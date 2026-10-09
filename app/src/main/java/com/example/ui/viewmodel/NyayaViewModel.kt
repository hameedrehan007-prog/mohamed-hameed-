package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AdrRequestEntity
import com.example.data.local.LawyerRequestEntity
import com.example.data.local.LegalDeadlineEntity
import com.example.data.local.NyayaDatabase
import com.example.data.local.RefundCaseEntity
import com.example.data.local.SavedComplaintEntity
import com.example.data.model.AppLanguage
import com.example.data.remote.LegalAiAssistant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class NyayaScreen {
    WELCOME_AUTH,
    HOME_DASHBOARD,
    ASK_LAWYER,
    DRAFT_COMPLAINT,
    FIND_REMEDY,
    KNOW_RIGHTS,
    KNOW_REMEDY,
    RESOLVE_DISPUTE,
    IMMEDIATE_HELP,
    REFUND_RECOVERY,
    POLICE_REFUSAL,
    DEADLINE_TRACKER,
    LEGAL_AID_MAP,
    PRIVACY_AND_SOURCES
}

class NyayaViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = NyayaDatabase.getInstance(application).nyayaDao()
    private val prefs = application.getSharedPreferences("nyaya_prefs", Context.MODE_PRIVATE)

    private val _language = MutableStateFlow(
        if (prefs.getString("lang", "en") == "hi") AppLanguage.HINDI else AppLanguage.ENGLISH
    )
    val language: StateFlow<AppLanguage> = _language.asStateFlow()

    private val _currentScreen = MutableStateFlow(
        if (prefs.getBoolean("is_logged_in", false)) NyayaScreen.HOME_DASHBOARD else NyayaScreen.WELCOME_AUTH
    )
    val currentScreen: StateFlow<NyayaScreen> = _currentScreen.asStateFlow()

    private val _loggedInUserPhone = MutableStateFlow(prefs.getString("user_phone", "") ?: "")
    val loggedInUserPhone: StateFlow<String> = _loggedInUserPhone.asStateFlow()

    private val _isGuestDemoSession = MutableStateFlow(prefs.getBoolean("is_guest_demo", false))
    val isGuestDemoSession: StateFlow<Boolean> = _isGuestDemoSession.asStateFlow()

    // AI Assistant state for Know My Rights / Find My Remedy custom queries
    private val _aiLoading = MutableStateFlow(false)
    val aiLoading: StateFlow<Boolean> = _aiLoading.asStateFlow()

    private val _aiResponseText = MutableStateFlow<String?>(null)
    val aiResponseText: StateFlow<String?> = _aiResponseText.asStateFlow()

    private val _aiErrorNote = MutableStateFlow<String?>(null)
    val aiErrorNote: StateFlow<String?> = _aiErrorNote.asStateFlow()

    // Reactive Flows from Room
    val lawyerRequests: StateFlow<List<LawyerRequestEntity>> = dao.getAllLawyerRequests()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val savedComplaints: StateFlow<List<SavedComplaintEntity>> = dao.getAllComplaints()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val refundCases: StateFlow<List<RefundCaseEntity>> = dao.getAllRefundCases()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val legalDeadlines: StateFlow<List<LegalDeadlineEntity>> = dao.getAllDeadlines()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val adrRequests: StateFlow<List<AdrRequestEntity>> = dao.getAllAdrRequests()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        seedInitialSampleDataIfEmpty()
    }

    private fun seedInitialSampleDataIfEmpty() {
        viewModelScope.launch {
            if (!prefs.getBoolean("seeded_initial_v1", false)) {
                dao.insertDeadline(
                    LegalDeadlineEntity(
                        caseTitle = "[DEMO SAMPLE] Consumer Notice Reply Window – Defective Appliance",
                        caseType = "Consumer Complaint",
                        incidentOrOrderDate = "2026-10-01",
                        establishedDeadlineDate = "2026-10-16 (15-Day Notice Period)",
                        isDeadlineConfirmedByLawyer = false,
                        documentsChecklist = "Tax Invoice, Warranty Card, Service Center Job Sheet, Speed Post Receipt",
                        reminderNote = "If seller does not refund within 15 days of notice delivery, file on e-Jagriti (Verify 2-year statutory limitation under Sec 69 CPA 2019 with lawyer)."
                    )
                )
                prefs.edit().putBoolean("seeded_initial_v1", true).apply()
            }
        }
    }

    fun toggleLanguage() {
        val next = if (_language.value == AppLanguage.ENGLISH) AppLanguage.HINDI else AppLanguage.ENGLISH
        _language.value = next
        prefs.edit().putString("lang", next.code).apply()
    }

    fun setLanguage(lang: AppLanguage) {
        _language.value = lang
        prefs.edit().putString("lang", lang.code).apply()
    }

    fun navigateTo(screen: NyayaScreen) {
        _currentScreen.value = screen
    }

    fun navigateBackToHome() {
        _currentScreen.value = NyayaScreen.HOME_DASHBOARD
    }

    fun enterGuestDemoMode(phoneOptional: String = "") {
        val label = if (phoneOptional.isNotBlank()) "$phoneOptional (Demo Session)" else "Guest / Demo Mode"
        _loggedInUserPhone.value = label
        _isGuestDemoSession.value = true
        prefs.edit()
            .putBoolean("is_logged_in", true)
            .putBoolean("is_guest_demo", true)
            .putString("user_phone", label)
            .apply()
        _currentScreen.value = NyayaScreen.HOME_DASHBOARD
    }

    fun logout() {
        _loggedInUserPhone.value = ""
        _isGuestDemoSession.value = false
        prefs.edit()
            .putBoolean("is_logged_in", false)
            .putBoolean("is_guest_demo", false)
            .remove("user_phone")
            .apply()
        _currentScreen.value = NyayaScreen.WELCOME_AUTH
    }

    private fun generateRefNumber(prefix: String): String {
        val datePart = SimpleDateFormat("yyyyMMdd", Locale.US).format(Date())
        val randPart = (1000..9999).random()
        return "NS-$prefix-$datePart-$randPart"
    }

    fun submitLawyerRequest(
        userName: String,
        mobileNumber: String,
        state: String,
        district: String,
        city: String,
        locality: String,
        problemType: String,
        description: String,
        preferredLanguage: String,
        assistanceType: String,
        freeAidEligible: Boolean,
        freeAidCriteria: String,
        consentGiven: Boolean,
        onComplete: (String) -> Unit
    ) {
        viewModelScope.launch {
            val ref = generateRefNumber("LAW")
            val entity = LawyerRequestEntity(
                referenceNumber = ref,
                userName = userName.trim(),
                mobileNumber = mobileNumber.trim(),
                state = state,
                district = district.trim(),
                city = city.trim(),
                locality = locality.trim(),
                problemType = problemType,
                description = description.trim(),
                preferredLanguage = preferredLanguage,
                assistanceType = assistanceType,
                freeAidEligible = freeAidEligible,
                freeAidCriteria = freeAidCriteria,
                consentGiven = consentGiven,
                status = "SAVED_LOCALLY_READY_FOR_DLSA_SUBMISSION"
            )
            dao.insertLawyerRequest(entity)
            onComplete(ref)
        }
    }

    fun saveComplaintDraft(
        title: String,
        complainantName: String,
        respondentName: String,
        authorityForum: String,
        incidentDate: String,
        draftText: String
    ) {
        viewModelScope.launch {
            dao.insertComplaint(
                SavedComplaintEntity(
                    title = title,
                    complainantName = complainantName,
                    respondentName = respondentName,
                    authorityForum = authorityForum,
                    incidentDate = incidentDate,
                    draftText = draftText
                )
            )
        }
    }

    fun deleteComplaintDraft(id: Int) {
        viewModelScope.launch {
            dao.deleteComplaint(id)
        }
    }

    fun saveRefundCase(
        sellerName: String,
        transactionId: String,
        paymentDate: String,
        amountInr: String,
        issueSummary: String,
        noticeDraft: String,
        onComplete: (String) -> Unit
    ) {
        viewModelScope.launch {
            val ref = generateRefNumber("RFD")
            dao.insertRefundCase(
                RefundCaseEntity(
                    referenceNumber = ref,
                    sellerName = sellerName,
                    transactionId = transactionId,
                    paymentDate = paymentDate,
                    amountInr = amountInr,
                    issueSummary = issueSummary,
                    noticeDraft = noticeDraft,
                    status = "NOTICE_GENERATED_AWAITING_SELLER_RESPONSE"
                )
            )
            onComplete(ref)
        }
    }

    fun deleteRefundCase(id: Int) {
        viewModelScope.launch {
            dao.deleteRefundCase(id)
        }
    }

    fun addLegalDeadline(
        caseTitle: String,
        caseType: String,
        incidentOrOrderDate: String,
        establishedDeadlineDate: String,
        isConfirmedByLawyer: Boolean,
        documentsChecklist: String,
        reminderNote: String
    ) {
        viewModelScope.launch {
            dao.insertDeadline(
                LegalDeadlineEntity(
                    caseTitle = caseTitle,
                    caseType = caseType,
                    incidentOrOrderDate = incidentOrOrderDate,
                    establishedDeadlineDate = establishedDeadlineDate,
                    isDeadlineConfirmedByLawyer = isConfirmedByLawyer,
                    documentsChecklist = documentsChecklist,
                    reminderNote = reminderNote
                )
            )
        }
    }

    fun toggleDeadlineCompleted(id: Int, completed: Boolean) {
        viewModelScope.launch {
            dao.updateDeadlineCompleted(id, completed)
        }
    }

    fun deleteDeadline(id: Int) {
        viewModelScope.launch {
            dao.deleteDeadline(id)
        }
    }

    fun submitAdrRequest(
        disputeAbout: String,
        partiesInvolved: String,
        locationStateDistrict: String,
        preferredAdrMode: String,
        needsClinicHelp: Boolean,
        onComplete: (String) -> Unit
    ) {
        viewModelScope.launch {
            val ref = generateRefNumber("ADR")
            dao.insertAdrRequest(
                AdrRequestEntity(
                    referenceNumber = ref,
                    disputeAbout = disputeAbout,
                    partiesInvolved = partiesInvolved,
                    locationStateDistrict = locationStateDistrict,
                    preferredAdrMode = preferredAdrMode,
                    needsClinicHelp = needsClinicHelp,
                    statusNote = "LOCAL_RECORD_CREATED_READY_FOR_MEDIATION_CENTRE_FILING"
                )
            )
            onComplete(ref)
        }
    }

    fun askCustomLegalQuestion(question: String) {
        if (question.isBlank()) return
        viewModelScope.launch {
            _aiLoading.value = true
            _aiResponseText.value = null
            _aiErrorNote.value = null
            val result = LegalAiAssistant.askLegalInformation(question, _language.value.code)
            _aiLoading.value = false
            result.onSuccess { text ->
                _aiResponseText.value = text
            }.onFailure { err ->
                _aiErrorNote.value = err.message
            }
        }
    }

    fun clearAiResponse() {
        _aiResponseText.value = null
        _aiErrorNote.value = null
    }

    fun deleteAllPersonalData(onDeleted: () -> Unit) {
        viewModelScope.launch {
            dao.clearAllLawyerRequests()
            dao.clearAllComplaints()
            dao.clearAllRefundCases()
            dao.clearAllDeadlines()
            dao.clearAllAdrRequests()
            onDeleted()
        }
    }
}
