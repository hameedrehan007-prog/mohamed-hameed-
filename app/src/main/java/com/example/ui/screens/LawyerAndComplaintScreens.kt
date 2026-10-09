package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.data.local.LawyerRequestEntity
import com.example.data.local.SavedComplaintEntity
import com.example.data.model.AppLanguage
import com.example.data.model.LegalDirectoryEntry
import com.example.data.repository.VerifiedLegalKnowledgeBase
import com.example.ui.components.LegalInformationDisclaimerBanner
import com.example.ui.components.VerificationBadgeChip
import com.example.ui.components.copyTextToClipboard
import com.example.ui.components.dialHelplineNumber
import com.example.ui.components.openOfficialUrl
import com.example.ui.components.shareOrExportDocument
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AskLawyerScreen(
    language: AppLanguage,
    savedRequests: List<LawyerRequestEntity>,
    onSubmitRequest: (
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
    ) -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var fullName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var selectedState by remember { mutableStateOf("Delhi (NCT)") }
    var district by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var locality by remember { mutableStateOf("") }
    var selectedProblemType by remember { mutableStateOf("Consumer Complaints") }
    var issueDescription by remember { mutableStateOf("") }
    var preferredLang by remember { mutableStateOf("Hindi & English") }
    var assistanceType by remember { mutableStateOf("Free Legal Aid (DLSA Panel Advocate)") }

    // Section 12 Eligibility checkboxes
    var isWomanOrChild by remember { mutableStateOf(false) }
    var isScStMember by remember { mutableStateOf(false) }
    var isPersonWithDisability by remember { mutableStateOf(false) }
    var isLowAnnualIncome by remember { mutableStateOf(false) }
    var isVictimOfTraffickingOrDisaster by remember { mutableStateOf(false) }

    var consentChecked by remember { mutableStateOf(false) }
    var includeDemoEntries by remember { mutableStateOf(false) }

    var formError by remember { mutableStateOf<String?>(null) }
    var latestReferenceNumber by remember { mutableStateOf<String?>(null) }

    val problemTypes = listOf(
        "Consumer Complaints",
        "Employment & Workplace Disputes",
        "Rental & Property Problems",
        "Family Disputes",
        "Cybercrime & Online Fraud",
        "Banking & Payment Problems",
        "Police-Related Complaints",
        "Other Civil / Criminal Matter"
    )

    val assistanceTypes = listOf(
        "Free Legal Aid (DLSA Panel Advocate)",
        "Pre-Litigation Mediation / Lok Adalat",
        "Legal Drafting & Notice Consultation",
        "Private Advocate Directory Search"
    )

    val isEligibleForFreeAid = isWomanOrChild || isScStMember || isPersonWithDisability || isLowAnnualIncome || isVictimOfTraffickingOrDisaster

    // Filter directory entries by State & Problem Type
    val matchingDirectory = remember(selectedState, selectedProblemType, includeDemoEntries) {
        VerifiedLegalKnowledgeBase.verifiedDirectoryEntries.filter { entry ->
            val stateMatches = selectedState == "All States / National" ||
                entry.state.equals(selectedState, ignoreCase = true) ||
                entry.category == "NALSA"
            val demoFilter = if (includeDemoEntries) true else entry.isVerifiedOfficial
            stateMatches && demoFilter
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "वकील और विधिक सहायता अनुरोध (Ask a Lawyer & Legal Aid)"
                    } else {
                        "Ask a Lawyer & Legal Aid Assessment"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "अपनी समस्या का विवरण भरें, निःशुल्क कानूनी सहायता (धारा 12) की पात्रता जांचें और अपने राज्य के सत्यापित विधिक सेवा प्राधिकरण (SLSA/DLSA) खोजें।"
                    } else {
                        "Complete your case intake, check eligibility for 100% Free Legal Aid under Section 12 of the Legal Services Authorities Act, 1987, and find verified nearby authorities."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Personal & Contact Details
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it; formError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "पूरा नाम (Full Name) *" else "Full Name *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("lawyer_name_input")
                )

                OutlinedTextField(
                    value = mobileNumber,
                    onValueChange = {
                        if (it.length <= 10 && it.all { ch -> ch.isDigit() }) {
                            mobileNumber = it
                            formError = null
                        }
                    },
                    label = { Text(if (language == AppLanguage.HINDI) "10-अंकों का मोबाइल नंबर *" else "10-Digit Mobile Number *") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("lawyer_mobile_input")
                )

                // State Selection Chips
                Text(
                    text = if (language == AppLanguage.HINDI) "राज्य चुनें (Select State) *" else "Select State / UT *",
                    style = MaterialTheme.typography.labelLarge
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    VerifiedLegalKnowledgeBase.indianStates.take(8).forEach { st ->
                        FilterChip(
                            selected = selectedState == st,
                            onClick = { selectedState = st },
                            label = { Text(st) }
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = district,
                        onValueChange = { district = it },
                        label = { Text(if (language == AppLanguage.HINDI) "जिला (District) *" else "District *") },
                        singleLine = true,
                        modifier = Modifier.weight(1f).testTag("lawyer_district_input")
                    )
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text(if (language == AppLanguage.HINDI) "शहर (City)" else "City / Town") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = locality,
                    onValueChange = { locality = it },
                    label = { Text(if (language == AppLanguage.HINDI) "मोहल्ला / क्षेत्र (Locality / Tehsil)" else "Locality / Tehsil") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Legal Problem Category
                Text(
                    text = if (language == AppLanguage.HINDI) "कानूनी समस्या का प्रकार (Type of Legal Problem) *" else "Type of Legal Problem *",
                    style = MaterialTheme.typography.labelLarge
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    problemTypes.forEach { prob ->
                        FilterChip(
                            selected = selectedProblemType == prob,
                            onClick = { selectedProblemType = prob },
                            label = { Text(prob) }
                        )
                    }
                }

                OutlinedTextField(
                    value = issueDescription,
                    onValueChange = { issueDescription = it; formError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "समस्या का संक्षिप्त विवरण (Short Description) *" else "Short Description of the Issue *") },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth().testTag("lawyer_description_input")
                )

                // Preferred Language & Assistance Type
                Text(
                    text = if (language == AppLanguage.HINDI) "पसंदीदा सहायता का प्रकार (Preferred Assistance Type)" else "Preferred Lawyer / Assistance Type",
                    style = MaterialTheme.typography.labelLarge
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    assistanceTypes.forEach { type ->
                        FilterChip(
                            selected = assistanceType == type,
                            onClick = { assistanceType = type },
                            label = { Text(type) }
                        )
                    }
                }

                HorizontalDivider()

                // Free Legal Aid Eligibility (Section 12 of Legal Services Authorities Act, 1987)
                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "निःशुल्क विधिक सहायता पात्रता जांच (विधिक सेवा प्राधिकरण अधिनियम, 1987 की धारा 12)"
                    } else {
                        "Free Legal Aid Eligibility Check (Section 12, Legal Services Authorities Act, 1987)"
                    },
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                EligibilityCheckRow(
                    checked = isWomanOrChild,
                    onCheckedChange = { isWomanOrChild = it },
                    text = if (language == AppLanguage.HINDI) "महिला या बच्चा (18 वर्ष से कम) – आय की परवाह किए बिना पात्र" else "Woman or Child (under 18 years) – Eligible regardless of income"
                )
                EligibilityCheckRow(
                    checked = isScStMember,
                    onCheckedChange = { isScStMember = it },
                    text = if (language == AppLanguage.HINDI) "अनुसूचित जाति (SC) या अनुसूचित जनजाति (ST) के सदस्य" else "Member of Scheduled Caste (SC) or Scheduled Tribe (ST)"
                )
                EligibilityCheckRow(
                    checked = isPersonWithDisability,
                    onCheckedChange = { isPersonWithDisability = it },
                    text = if (language == AppLanguage.HINDI) "दिव्यांग व्यक्ति (Person with Benchmark Disability)" else "Person with Disability (under RPwD Act)"
                )
                EligibilityCheckRow(
                    checked = isLowAnnualIncome,
                    onCheckedChange = { isLowAnnualIncome = it },
                    text = if (language == AppLanguage.HINDI) "कम वार्षिक आय (राज्य विधिक सेवा सीमा के अंतर्गत, सामान्यतः ₹3 लाख से कम) या औद्योगिक श्रमिक" else "Low Annual Income (within State SLSA threshold) or Industrial Workman"
                )
                EligibilityCheckRow(
                    checked = isVictimOfTraffickingOrDisaster,
                    onCheckedChange = { isVictimOfTraffickingOrDisaster = it },
                    text = if (language == AppLanguage.HINDI) "आपदा, जातीय हिंसा या मानव तस्करी से पीड़ित या हिरासत में व्यक्ति" else "Victim of natural disaster, ethnic violence, trafficking, or person in custody"
                )

                Surface(
                    color = if (isEligibleForFreeAid) Color(0xFFD2F5E3) else MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = if (isEligibleForFreeAid) {
                            if (language == AppLanguage.HINDI) {
                                "✓ आप धारा 12 के तहत 100% निःशुल्क सरकारी पैनल वकील और कोर्ट फीस छूट के पात्र प्रतीत होते हैं!"
                            } else {
                                "✓ You qualify under Section 12 for 100% Free Legal Aid (free panel advocate & court fee exemption via DLSA/NALSA)!"
                            }
                        } else {
                            if (language == AppLanguage.HINDI) {
                                "यदि आप धारा 12 में नहीं आते, तब भी आप DLSA प्री-लिटिगेशन मध्यस्थता और लोक अदालत की निःशुल्क सलाह ले सकते हैं।"
                            } else {
                                "Even if Section 12 criteria are not selected, you can still use DLSA Pre-Litigation Mediation, Lok Adalat, and legal literacy clinics."
                            }
                        },
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isEligibleForFreeAid) Color(0xFF0B5D3F) else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                // Consent Checkbox (Required by Rule 14)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = consentChecked,
                        onCheckedChange = { consentChecked = it; formError = null },
                        modifier = Modifier.testTag("lawyer_consent_checkbox")
                    )
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "मैं सहमति देता/देती हूं कि यह विवरण मेरे डिवाइस पर सुरक्षित किया जाए ताकि मैं इसे DLSA/वकील के समक्ष प्रस्तुत कर सकूं।"
                        } else {
                            "I consent to saving these case details locally to generate a structured referral summary for DLSA / lawyer consultation."
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                if (formError != null) {
                    Text(
                        text = formError!!,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Button(
                    onClick = {
                        if (fullName.isBlank()) {
                            formError = if (language == AppLanguage.HINDI) "कृपया अपना नाम दर्ज करें।" else "Please enter your full name."
                            return@Button
                        }
                        if (mobileNumber.length != 10) {
                            formError = if (language == AppLanguage.HINDI) "कृपया 10-अंकों का मान्य मोबाइल नंबर दर्ज करें।" else "Please enter a valid 10-digit mobile number."
                            return@Button
                        }
                        if (district.isBlank()) {
                            formError = if (language == AppLanguage.HINDI) "कृपया अपना जिला दर्ज करें।" else "Please enter your district."
                            return@Button
                        }
                        if (issueDescription.isBlank()) {
                            formError = if (language == AppLanguage.HINDI) "कृपया समस्या का संक्षिप्त विवरण लिखें।" else "Please provide a short description of your legal issue."
                            return@Button
                        }
                        if (!consentChecked) {
                            formError = if (language == AppLanguage.HINDI) "कृपया आगे बढ़ने के लिए सहमति बॉक्स चुनें।" else "Please check the consent box before generating your referral."
                            return@Button
                        }

                        val criteriaList = mutableListOf<String>()
                        if (isWomanOrChild) criteriaList.add("Sec 12(c) Woman/Child")
                        if (isScStMember) criteriaList.add("Sec 12(a) SC/ST")
                        if (isPersonWithDisability) criteriaList.add("Sec 12(d) PwD")
                        if (isLowAnnualIncome) criteriaList.add("Sec 12(h) Low Income/Workman")
                        if (isVictimOfTraffickingOrDisaster) criteriaList.add("Sec 12(e) Disaster/Custody")

                        onSubmitRequest(
                            fullName,
                            mobileNumber,
                            selectedState,
                            district,
                            city,
                            locality,
                            selectedProblemType,
                            issueDescription,
                            preferredLang,
                            assistanceType,
                            isEligibleForFreeAid,
                            if (criteriaList.isEmpty()) "General Consultation" else criteriaList.joinToString(", "),
                            consentChecked
                        ) { ref ->
                            latestReferenceNumber = ref
                        }
                    },
                    modifier = Modifier.fillMaxWidth().testTag("submit_lawyer_request_button")
                ) {
                    Text(
                        if (language == AppLanguage.HINDI) "अनुरोध दर्ज करें और अगले कदम देखें"
                        else "Prepare Case Referral & View Next Steps"
                    )
                }
            }
        }

        // Next Steps Card after Submission (Not just "Submitted successfully")
        if (latestReferenceNumber != null) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD2F5E3)),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.5.dp, Color(0xFF0B5D3F))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF0B5D3F))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (language == AppLanguage.HINDI) {
                                "केस रेफरेंस तैयार: $latestReferenceNumber"
                            } else {
                                "Case Referral Docket Prepared: $latestReferenceNumber"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF04291B)
                        )
                    }
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "स्थिति (Status): डिवाइस पर सुरक्षित (SAVED_LOCALLY_READY_FOR_DLSA_SUBMISSION)। चूंकि यह ऐप किसी निजी वकील को बिना आपकी अनुमति डेटा नहीं भेजता, कृपया नीचे दिए गए व्यावहारिक कदम उठाएं:\n" +
                                "1. NALSA की राष्ट्रीय टोल-फ्री हेल्पलाइन 15100 पर कॉल करें या nalsa.gov.in पर ऑनलाइन आवेदन करें।\n" +
                                "2. अपने जिले के न्यायालय परिसर में स्थित 'DLSA फ्रंट ऑफिस' में पहचान पत्र और दस्तावेजों की फोटोकॉपी के साथ जाएं।\n" +
                                "3. यदि आप धारा 12 के पात्र हैं, तो DLSA सचिव द्वारा आपको निःशुल्क पैनल अधिवक्ता आवंटित किया जाएगा।"
                        } else {
                            "Status: SAVED_LOCALLY_READY_FOR_DLSA_SUBMISSION (Stored privately on your device; no third-party transmission without live backend).\n\n" +
                                "Actionable Next Steps:\n" +
                                "1. Call the verified NALSA Toll-Free Helpline 15100 or apply on the official portal (https://nalsa.gov.in).\n" +
                                "2. Visit the District Legal Services Authority (DLSA) Front Office inside your District Court complex with your ID proof and case documents.\n" +
                                "3. Present your summary below to request a Panel Advocate or Pre-Litigation Mediation."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF04291B)
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(onClick = { dialHelplineNumber(context, "15100") }) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Call 15100")
                        }
                        OutlinedButton(onClick = { openOfficialUrl(context, "https://nalsa.gov.in") }) {
                            Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("nalsa.gov.in")
                        }
                    }
                }
            }
        }

        // Nearby Legal Aid & Verified Directory Results
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (language == AppLanguage.HINDI) {
                                "सत्यापित विधिक सहायता और वकील निर्देशिका ($selectedState)"
                            } else {
                                "Verified Legal Aid & Directory ($selectedState)"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (language == AppLanguage.HINDI) {
                                "सत्यापित सरकारी प्राधिकरण बनाम डेमो प्रविष्टियां स्पष्ट रूप से चिह्नित हैं"
                            } else {
                                "Verified statutory authorities are strictly distinguished from demo profiles"
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (language == AppLanguage.HINDI) "डेमो दिखाएं" else "Show Demo",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Switch(
                            checked = includeDemoEntries,
                            onCheckedChange = { includeDemoEntries = it },
                            modifier = Modifier.testTag("toggle_demo_lawyers_switch")
                        )
                    }
                }

                if (matchingDirectory.isEmpty()) {
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "इस राज्य के लिए कोई विशिष्ट प्रविष्टि नहीं मिली। कृपया राष्ट्रीय विधिक सेवा प्राधिकरण (NALSA) हेल्पलाइन 15100 पर कॉल करें।"
                        } else {
                            "No local directory entry found for this filter. Please contact the National Legal Services Authority (NALSA) Helpline at 15100 or visit your nearest District Court DLSA Front Office."
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    matchingDirectory.forEach { entry ->
                        DirectoryEntryCard(entry = entry, language = language)
                    }
                }
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}

@Composable
private fun EligibilityCheckRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text = text, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun DirectoryEntryCard(
    entry: LegalDirectoryEntry,
    language: AppLanguage
) {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (entry.isVerifiedOfficial) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
            else Color(0xFFFFF8E1)
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            1.dp,
            if (entry.isVerifiedOfficial) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            else Color(0xFFE65100).copy(alpha = 0.4f)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            VerificationBadgeChip(
                isVerifiedOfficial = entry.isVerifiedOfficial,
                label = entry.verificationBadgeText.get(language)
            )
            Text(
                text = entry.name.get(language),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${entry.city}, ${entry.district}, ${entry.state} • ${entry.practiceAreas.joinToString(", ")}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = entry.address.get(language),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = entry.operatingHours.get(language),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = entry.appointmentInfo.get(language),
                style = MaterialTheme.typography.bodySmall
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                if (entry.contactPhone.isNotBlank() && entry.isVerifiedOfficial) {
                    Button(
                        onClick = { dialHelplineNumber(context, entry.contactPhone) }
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(entry.contactPhone)
                    }
                }
                if (entry.officialWebsiteOrEmail.startsWith("http")) {
                    OutlinedButton(
                        onClick = { openOfficialUrl(context, entry.officialWebsiteOrEmail) }
                    ) {
                        Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (language == AppLanguage.HINDI) "आधिकारिक वेबसाइट" else "Official Website")
                    }
                }
            }
        }
    }
}

@Composable
fun DraftComplaintScreen(
    language: AppLanguage,
    savedComplaints: List<SavedComplaintEntity>,
    onSaveDraft: (String, String, String, String, String, String) -> Unit,
    onDeleteDraft: (Int) -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var complainantName by remember { mutableStateOf("") }
    var complainantAddressPhone by remember { mutableStateOf("") }
    var respondentName by remember { mutableStateOf("") }
    var respondentAddress by remember { mutableStateOf("") }
    var incidentDate by remember { mutableStateOf("") }
    var incidentLocation by remember { mutableStateOf("") }
    var chronologicalFacts by remember { mutableStateOf("") }
    var supportingDocuments by remember { mutableStateOf("") }
    var previousActionsTaken by remember { mutableStateOf("") }
    var reliefRequested by remember { mutableStateOf("") }
    var authorityForum by remember { mutableStateOf("Before the District Consumer Disputes Redressal Commission / Competent Authority") }

    var editableGeneratedDraft by remember { mutableStateOf("") }
    var clarifyingPrompts by remember { mutableStateOf<List<String>>(emptyList()) }
    var validationError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "औपचारिक शिकायत प्रारूपण टूल (Draft My Complaint)"
                    } else {
                        "Draft My Complaint (Formal Petition Format)"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "यह टूल केवल आपके द्वारा दिए गए वास्तविक तथ्यों के आधार पर औपचारिक शिकायत तैयार करता है। यह स्वयं कोई तथ्य या सबूत नहीं गढ़ता।"
                    } else {
                        "Generates a structured, editable petition-style complaint strictly from your stated facts without inventing facts, evidence, or unsupported legal sections."
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedTextField(
                    value = authorityForum,
                    onValueChange = { authorityForum = it },
                    label = { Text(if (language == AppLanguage.HINDI) "प्राधिकरण / मंच (Authority or Forum, if known)" else "Relevant Authority or Forum (if known)") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = complainantName,
                    onValueChange = { complainantName = it; validationError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "शिकायतकर्ता का नाम (Complainant Name) *" else "Complainant Full Name *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("complaint_complainant_name")
                )

                OutlinedTextField(
                    value = complainantAddressPhone,
                    onValueChange = { complainantAddressPhone = it },
                    label = { Text(if (language == AppLanguage.HINDI) "शिकायतकर्ता का पता और मोबाइल नंबर *" else "Complainant Address & Contact Number *") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = respondentName,
                    onValueChange = { respondentName = it },
                    label = { Text(if (language == AppLanguage.HINDI) "विपक्षी का नाम (Respondent Name, if known)" else "Respondent / Opposite Party Name (if known)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("complaint_respondent_name")
                )

                OutlinedTextField(
                    value = respondentAddress,
                    onValueChange = { respondentAddress = it },
                    label = { Text(if (language == AppLanguage.HINDI) "विपक्षी का पता / विवरण" else "Respondent Address / Details (if known)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = incidentDate,
                        onValueChange = { incidentDate = it },
                        label = { Text(if (language == AppLanguage.HINDI) "घटना की तिथि (Date) *" else "Incident Date *") },
                        placeholder = { Text("YYYY-MM-DD") },
                        singleLine = true,
                        modifier = Modifier.weight(1f).testTag("complaint_incident_date")
                    )
                    OutlinedTextField(
                        value = incidentLocation,
                        onValueChange = { incidentLocation = it },
                        label = { Text(if (language == AppLanguage.HINDI) "घटना का स्थान (Place) *" else "Incident Location *") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = chronologicalFacts,
                    onValueChange = { chronologicalFacts = it; validationError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "घटना का कालानुक्रमिक विवरण (Facts in Chronological Order) *" else "Facts in Chronological Order *") },
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth().testTag("complaint_facts_input")
                )

                OutlinedTextField(
                    value = supportingDocuments,
                    onValueChange = { supportingDocuments = it },
                    label = { Text(if (language == AppLanguage.HINDI) "समर्थक दस्तावेज / साक्ष्य (Supporting Documents / Annexures)" else "Supporting Documents / Evidence (comma or line separated)") },
                    placeholder = { Text("1. Invoice Copy, 2. Bank Statement, 3. Email Notice") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = previousActionsTaken,
                    onValueChange = { previousActionsTaken = it },
                    label = { Text(if (language == AppLanguage.HINDI) "पूर्व में की गई कार्रवाई (Previous Actions Taken)" else "Previous Actions Taken (e.g., Written email, Helpline call)") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = reliefRequested,
                    onValueChange = { reliefRequested = it; validationError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "मांगी गई राहत / कार्रवाई (Relief or Action Requested) *" else "Relief or Action Requested *") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth().testTag("complaint_relief_input")
                )

                if (validationError != null) {
                    Text(
                        text = validationError!!,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Button(
                    onClick = {
                        if (complainantName.isBlank() || chronologicalFacts.isBlank() || reliefRequested.isBlank()) {
                            validationError = if (language == AppLanguage.HINDI) {
                                "कृपया शिकायतकर्ता का नाम, घटना के तथ्य और मांगी गई राहत अवश्य भरें।"
                            } else {
                                "Please enter Complainant Name, Chronological Facts, and Relief Requested."
                            }
                            return@Button
                        }

                        // Clarifying questions check without inventing facts
                        val questions = mutableListOf<String>()
                        if (incidentDate.isBlank()) {
                            questions.add(
                                if (language == AppLanguage.HINDI) "• घटना या लेनदेन की सटीक तारीख (Date) जोड़ने से समय-सीमा (Limitation) स्पष्ट होती है।"
                                else "• Adding the exact Incident/Transaction Date helps establish statutory limitation periods."
                            )
                        }
                        if (supportingDocuments.isBlank()) {
                            questions.add(
                                if (language == AppLanguage.HINDI) "• कृपया उन दस्तावेजों/रसीदों का उल्लेख करें जिन्हें आप संलग्नक (Annexures) के रूप में लगाएंगे।"
                                else "• Consider listing specific receipts, screenshots, or notices you will attach as Annexures."
                            )
                        }
                        if (previousActionsTaken.isBlank()) {
                            questions.add(
                                if (language == AppLanguage.HINDI) "• क्या आपने पहले विपक्षी को कोई लिखित नोटिस या ईमेल भेजा था? यदि हां, तो उसकी तारीख लिखें।"
                                else "• Did you send any prior written complaint/notice to the opposite party? Including that date strengthens your petition."
                            )
                        }
                        clarifyingPrompts = questions

                        val todayDate = SimpleDateFormat("dd-MM-yyyy", Locale.US).format(Date())
                        val respDisplay = respondentName.ifBlank { "[Name of Opposite Party / Respondent Not Known]" }
                        val respAddrDisplay = respondentAddress.ifBlank { "[Address to be inserted]" }
                        val annexureList = if (supportingDocuments.isBlank()) {
                            "1. [Attach supporting document copies if available]"
                        } else {
                            supportingDocuments.split(",", "\n").mapIndexed { idx, item ->
                                "Annexure C-${idx + 1}: ${item.trim()}"
                            }.joinToString("\n")
                        }

                        editableGeneratedDraft = """
BEFORE THE ${authorityForum.uppercase(Locale.getDefault())}

IN THE MATTER OF:
COMPLAINT / REPRESENTATION FILED BY:
Name: $complainantName
Address & Contact: ${complainantAddressPhone.ifBlank { "[Insert Address & Mobile Number]" }}
                                                        ... COMPLAINANT / APPLICANT

VERSUS

Name: $respDisplay
Address: $respAddrDisplay
                                                        ... RESPONDENT / OPPOSITE PARTY

SUBJECT: FORMAL COMPLAINT REGARDING INCIDENT DATED ${incidentDate.ifBlank { "[INSERT DATE]" }} AT ${incidentLocation.ifBlank { "[INSERT LOCATION]" }}

RESPECTFULLY SHOWETH:

1. DETAILS OF PARTIES:
   That the Complainant above-named is a law-abiding citizen submitting this representation based on personal knowledge and records. The Respondent is the party against whom grievance/relief is sought.

2. CHRONOLOGICAL STATEMENT OF FACTS:
   a) Date & Place of Incident/Cause of Action: ${incidentDate.ifBlank { "[To be specified]" }} at ${incidentLocation.ifBlank { "[To be specified]" }}.
   b) Facts of the Case:
   $chronologicalFacts

3. PREVIOUS EFFORTS / ACTIONS TAKEN BY COMPLAINANT:
   ${previousActionsTaken.ifBlank { "The Complainant attempted to resolve the grievance amicably, but no satisfactory resolution was provided." }}

4. GROUNDS OF COMPLAINT:
   a) That the acts/omissions described above have caused wrongful loss, grievance, and hardship to the Complainant.
   b) [Legal Provision Note: Specific statutory sections may be inserted after verification by a qualified advocate based on the forum's jurisdiction.]

5. RELIEF / ACTION REQUESTED (PRAYER):
   It is therefore most respectfully prayed that this Hon'ble Authority / Forum may graciously be pleased to:
   $reliefRequested

6. LIST OF SUPPORTING DOCUMENTS (ANNEXURES):
$annexureList

VERIFICATION / DECLARATION:
I, $complainantName, the Complainant above-named, do hereby verify that the contents of paragraphs 1 to 6 above are true and correct to the best of my knowledge and belief, and nothing material has been concealed therefrom.

Place: ${incidentLocation.ifBlank { "[Place]" }}
Date: $todayDate

                                                        ___________________________
                                                        Signature of Complainant
                                                        ($complainantName)

[DISCLAIMER: This draft is prepared based on user-supplied facts and should be reviewed by a qualified lawyer or DLSA Panel Advocate before formal filing.]
                        """.trimIndent()
                    },
                    modifier = Modifier.fillMaxWidth().testTag("generate_complaint_draft_button")
                ) {
                    Text(
                        if (language == AppLanguage.HINDI) "औपचारिक शिकायत प्रारूप तैयार करें (Generate Petition Draft)"
                        else "Generate Structured Petition Draft"
                    )
                }
            }
        }

        if (clarifyingPrompts.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = if (language == AppLanguage.HINDI) "स्पष्टीकरण सुझाव (Clarifying Prompts to Strengthen Your Draft):" else "Clarifying Questions to Strengthen Your Draft:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    clarifyingPrompts.forEach { q ->
                        Text(text = q, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onTertiaryContainer)
                    }
                }
            }
        }

        if (editableGeneratedDraft.isNotBlank()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Edit, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (language == AppLanguage.HINDI) "पूर्वावलोकन और संपादन (Preview & Edit Draft)" else "Editable Petition Preview",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    OutlinedTextField(
                        value = editableGeneratedDraft,
                        onValueChange = { editableGeneratedDraft = it },
                        textStyle = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                        minLines = 14,
                        modifier = Modifier.fillMaxWidth().testTag("editable_complaint_preview")
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                copyTextToClipboard(
                                    context,
                                    "Complaint Draft",
                                    editableGeneratedDraft,
                                    if (language == AppLanguage.HINDI) "शिकायत प्रारूप कॉपी किया गया" else "Complaint draft copied to clipboard"
                                )
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (language == AppLanguage.HINDI) "कॉपी करें" else "Copy")
                        }

                        OutlinedButton(
                            onClick = {
                                shareOrExportDocument(
                                    context,
                                    "Formal Complaint - $complainantName",
                                    editableGeneratedDraft
                                )
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (language == AppLanguage.HINDI) "साझा/डाउनलोड" else "Export")
                        }

                        Button(
                            onClick = {
                                onSaveDraft(
                                    "Complaint vs ${respondentName.ifBlank { "Opposite Party" }}",
                                    complainantName,
                                    respondentName,
                                    authorityForum,
                                    incidentDate,
                                    editableGeneratedDraft
                                )
                                copyTextToClipboard(
                                    context,
                                    "Saved",
                                    editableGeneratedDraft,
                                    if (language == AppLanguage.HINDI) "ड्राफ्ट सुरक्षित और कॉपी किया गया!" else "Draft saved to device & copied!"
                                )
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (language == AppLanguage.HINDI) "सहेजें" else "Save")
                        }
                    }
                }
            }
        }

        // Saved Drafts Section
        if (savedComplaints.isNotEmpty()) {
            Text(
                text = if (language == AppLanguage.HINDI) "सहेजे गए शिकायत प्रारूप (Saved Complaint Drafts)" else "Saved Complaint Drafts on Device",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            savedComplaints.forEach { item ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                            Text(
                                "${item.complainantName} • ${item.authorityForum}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Row {
                            IconButton(onClick = { editableGeneratedDraft = item.draftText }) {
                                Icon(Icons.Default.Edit, contentDescription = "Load Draft")
                            }
                            IconButton(onClick = { onDeleteDraft(item.id) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete Draft", tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}
