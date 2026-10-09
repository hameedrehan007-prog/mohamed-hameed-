package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.AppLanguage
import com.example.ui.components.NyayaTopAppBar
import com.example.ui.screens.AskLawyerScreen
import com.example.ui.screens.DeadlineTrackerScreen
import com.example.ui.screens.DraftComplaintScreen
import com.example.ui.screens.FindRemedyScreen
import com.example.ui.screens.HomeDashboardScreen
import com.example.ui.screens.ImmediateHelpScreen
import com.example.ui.screens.KnowRemedyScreen
import com.example.ui.screens.KnowRightsScreen
import com.example.ui.screens.LegalAidMapDirectoryScreen
import com.example.ui.screens.PoliceRefusalScreen
import com.example.ui.screens.PrivacyAndSourcesScreen
import com.example.ui.screens.RefundRecoveryScreen
import com.example.ui.screens.ResolveDisputeScreen
import com.example.ui.screens.WelcomeAuthScreen
import com.example.ui.theme.NyayaSahayakTheme
import com.example.ui.viewmodel.NyayaScreen
import com.example.ui.viewmodel.NyayaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NyayaSahayakTheme {
                NyayaSahayakApp()
            }
        }
    }
}

@Composable
fun NyayaSahayakApp(
    viewModel: NyayaViewModel = viewModel()
) {
    val language by viewModel.language.collectAsStateWithLifecycle()
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val userPhone by viewModel.loggedInUserPhone.collectAsStateWithLifecycle()

    val lawyerRequests by viewModel.lawyerRequests.collectAsStateWithLifecycle()
    val savedComplaints by viewModel.savedComplaints.collectAsStateWithLifecycle()
    val refundCases by viewModel.refundCases.collectAsStateWithLifecycle()
    val legalDeadlines by viewModel.legalDeadlines.collectAsStateWithLifecycle()
    val adrRequests by viewModel.adrRequests.collectAsStateWithLifecycle()

    val aiLoading by viewModel.aiLoading.collectAsStateWithLifecycle()
    val aiResponseText by viewModel.aiResponseText.collectAsStateWithLifecycle()
    val aiErrorNote by viewModel.aiErrorNote.collectAsStateWithLifecycle()

    if (currentScreen == NyayaScreen.WELCOME_AUTH) {
        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                WelcomeAuthScreen(
                    language = language,
                    onToggleLanguage = { viewModel.toggleLanguage() },
                    onEnterGuestDemo = { phone -> viewModel.enterGuestDemoMode(phone) }
                )
            }
        }
        return
    }

    // Handle system back button on all secondary screens
    if (currentScreen != NyayaScreen.HOME_DASHBOARD) {
        BackHandler {
            viewModel.clearAiResponse()
            viewModel.navigateBackToHome()
        }
    }

    val topBarTitle = when (currentScreen) {
        NyayaScreen.HOME_DASHBOARD -> if (language == AppLanguage.HINDI) "न्याय सहायक (NYAYA SAHAYAK)" else "NYAYA SAHAYAK"
        NyayaScreen.ASK_LAWYER -> if (language == AppLanguage.HINDI) "वकील से पूछें" else "Ask a Lawyer"
        NyayaScreen.DRAFT_COMPLAINT -> if (language == AppLanguage.HINDI) "मेरी शिकायत लिखें" else "Draft My Complaint"
        NyayaScreen.FIND_REMEDY -> if (language == AppLanguage.HINDI) "मेरा उपाय खोजें" else "Find My Remedy"
        NyayaScreen.KNOW_RIGHTS -> if (language == AppLanguage.HINDI) "मेरे अधिकार जानें" else "Know My Rights"
        NyayaScreen.KNOW_REMEDY -> if (language == AppLanguage.HINDI) "सही प्राधिकरण चुनें" else "Know My Remedy"
        NyayaScreen.RESOLVE_DISPUTE -> if (language == AppLanguage.HINDI) "विवाद सुलझाएं (ADR)" else "Resolve My Dispute (ADR)"
        NyayaScreen.IMMEDIATE_HELP -> if (language == AppLanguage.HINDI) "तत्काल सहायता और हेल्पलाइन" else "Immediate Help & Helplines"
        NyayaScreen.REFUND_RECOVERY -> if (language == AppLanguage.HINDI) "रिफंड वसूली सहायता" else "Refund Recovery Assistance"
        NyayaScreen.POLICE_REFUSAL -> if (language == AppLanguage.HINDI) "पुलिस अस्वीकृति सहायता" else "Police Refusal Assistance"
        NyayaScreen.DEADLINE_TRACKER -> if (language == AppLanguage.HINDI) "कानूनी समय-सीमा ट्रैकर" else "Legal Deadline Tracker"
        NyayaScreen.LEGAL_AID_MAP -> if (language == AppLanguage.HINDI) "विधिक सहायता निर्देशिका" else "Legal Aid Directory"
        NyayaScreen.PRIVACY_AND_SOURCES -> if (language == AppLanguage.HINDI) "गोपनीयता और कानूनी स्रोत" else "Privacy & Legal Sources"
        NyayaScreen.WELCOME_AUTH -> "NYAYA SAHAYAK"
    }

    val topBarSubtitle = if (currentScreen == NyayaScreen.HOME_DASHBOARD) {
        if (language == AppLanguage.HINDI) "अपने अधिकार समझें। अपना उपाय खोजें।"
        else "Understand Your Rights. Find Your Remedy."
    } else null

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            NyayaTopAppBar(
                title = topBarTitle,
                subtitle = topBarSubtitle,
                language = language,
                onToggleLanguage = { viewModel.toggleLanguage() },
                onBackClick = if (currentScreen != NyayaScreen.HOME_DASHBOARD) {
                    {
                        viewModel.clearAiResponse()
                        viewModel.navigateBackToHome()
                    }
                } else null,
                onEmergencyClick = if (currentScreen != NyayaScreen.IMMEDIATE_HELP) {
                    { viewModel.navigateTo(NyayaScreen.IMMEDIATE_HELP) }
                } else null
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                NyayaScreen.HOME_DASHBOARD -> {
                    HomeDashboardScreen(
                        language = language,
                        userSessionLabel = userPhone.ifBlank { "Guest / Demo Mode" },
                        activeDeadlinesCount = legalDeadlines.count { !it.isCompleted },
                        savedDraftsCount = savedComplaints.size,
                        onNavigate = { screen ->
                            viewModel.clearAiResponse()
                            viewModel.navigateTo(screen)
                        },
                        onLogout = { viewModel.logout() }
                    )
                }

                NyayaScreen.ASK_LAWYER -> {
                    AskLawyerScreen(
                        language = language,
                        savedRequests = lawyerRequests,
                        onSubmitRequest = { name, phone, state, dist, city, loc, prob, desc, lang, assist, elig, crit, consent, cb ->
                            viewModel.submitLawyerRequest(
                                name, phone, state, dist, city, loc, prob, desc, lang, assist, elig, crit, consent, cb
                            )
                        }
                    )
                }

                NyayaScreen.DRAFT_COMPLAINT -> {
                    DraftComplaintScreen(
                        language = language,
                        savedComplaints = savedComplaints,
                        onSaveDraft = { title, comp, resp, forum, date, text ->
                            viewModel.saveComplaintDraft(title, comp, resp, forum, date, text)
                        },
                        onDeleteDraft = { id -> viewModel.deleteComplaintDraft(id) }
                    )
                }

                NyayaScreen.FIND_REMEDY -> {
                    FindRemedyScreen(
                        language = language,
                        aiLoading = aiLoading,
                        aiResponseText = aiResponseText,
                        aiErrorNote = aiErrorNote,
                        onAskAi = { q -> viewModel.askCustomLegalQuestion(q) }
                    )
                }

                NyayaScreen.KNOW_RIGHTS -> {
                    KnowRightsScreen(
                        language = language,
                        aiLoading = aiLoading,
                        aiResponseText = aiResponseText,
                        aiErrorNote = aiErrorNote,
                        onAskAi = { q -> viewModel.askCustomLegalQuestion(q) }
                    )
                }

                NyayaScreen.KNOW_REMEDY -> {
                    KnowRemedyScreen(language = language)
                }

                NyayaScreen.RESOLVE_DISPUTE -> {
                    ResolveDisputeScreen(
                        language = language,
                        savedAdrRequests = adrRequests,
                        onSubmitAdr = { about, parties, loc, mode, clinic, cb ->
                            viewModel.submitAdrRequest(about, parties, loc, mode, clinic, cb)
                        }
                    )
                }

                NyayaScreen.IMMEDIATE_HELP -> {
                    ImmediateHelpScreen(language = language)
                }

                NyayaScreen.REFUND_RECOVERY -> {
                    RefundRecoveryScreen(
                        language = language,
                        savedRefundCases = refundCases,
                        onSaveRefundCase = { seller, txn, date, amt, issue, draft, cb ->
                            viewModel.saveRefundCase(seller, txn, date, amt, issue, draft, cb)
                        },
                        onDeleteRefundCase = { id -> viewModel.deleteRefundCase(id) }
                    )
                }

                NyayaScreen.POLICE_REFUSAL -> {
                    PoliceRefusalScreen(language = language)
                }

                NyayaScreen.DEADLINE_TRACKER -> {
                    DeadlineTrackerScreen(
                        language = language,
                        deadlines = legalDeadlines,
                        onAddDeadline = { title, type, orderDate, deadDate, confirmed, docs, note ->
                            viewModel.addLegalDeadline(title, type, orderDate, deadDate, confirmed, docs, note)
                        },
                        onToggleCompleted = { id, done -> viewModel.toggleDeadlineCompleted(id, done) },
                        onDeleteDeadline = { id -> viewModel.deleteDeadline(id) }
                    )
                }

                NyayaScreen.LEGAL_AID_MAP -> {
                    LegalAidMapDirectoryScreen(language = language)
                }

                NyayaScreen.PRIVACY_AND_SOURCES -> {
                    PrivacyAndSourcesScreen(
                        language = language,
                        onDeleteAllData = { viewModel.deleteAllPersonalData {} }
                    )
                }

                NyayaScreen.WELCOME_AUTH -> {}
            }
        }
    }
}
