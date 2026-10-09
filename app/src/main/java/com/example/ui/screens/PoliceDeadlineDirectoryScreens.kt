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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.data.local.LegalDeadlineEntity
import com.example.data.model.AppLanguage
import com.example.data.repository.VerifiedLegalKnowledgeBase
import com.example.ui.components.LegalInformationDisclaimerBanner
import com.example.ui.components.copyTextToClipboard
import com.example.ui.components.dialHelplineNumber
import com.example.ui.components.shareOrExportDocument
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PoliceRefusalScreen(
    language: AppLanguage
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var selectedState by remember { mutableStateOf("Delhi (NCT)") }
    var incidentCategory by remember { mutableStateOf("Serious Crime / Theft / Robbery / Cyber Fraud / Assault (Cognizable)") }
    var policeStationName by remember { mutableStateOf("") }
    var refusalDate by remember { mutableStateOf("") }
    var complainantName by remember { mutableStateOf("") }
    var incidentSummary by remember { mutableStateOf("") }
    var spDraftText by remember { mutableStateOf("") }

    val incidentCategories = listOf(
        "Serious Crime / Theft / Robbery / Cyber Fraud / Assault (Cognizable)",
        "Minor Verbal Altercation / Simple Non-Injury Dispute (Non-Cognizable)",
        "Property / Contract / Money Recovery / Tenancy Dispute (Civil Nature)",
        "Offence Occurred Outside Local Police Station Area (Zero FIR)"
    )

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
                        "पुलिस शिकायत अस्वीकृति सहायता (Police Refusal Assistance)"
                    } else {
                        "Police Refusal Assistance (BNSS, 2023)"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                // Crucial Legal Distinction Banner (Prompt Section 11)
                Surface(
                    color = Color(0xFFFFF3E0),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color(0xFFE65100).copy(alpha = 0.4f))
                ) {
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "महत्वपूर्ण कानूनी अंतर: पुलिस हर शिकायत पर तुरंत FIR दर्ज करने के लिए बाध्य नहीं है। भारतीय नागरिक सुरक्षा संहिता, 2023 (BNSS) के तहत केवल 'संज्ञेय अपराध' (Cognizable Offence) में FIR अनिवार्य है। 'असंज्ञेय' (Non-Cognizable) मामलों में NCR दर्ज होता है, और विशुद्ध सिविल विवाद (जैसे किराया/उधार) सिविल न्यायालय या मध्यस्थता में जाते हैं।"
                        } else {
                            "Important Legal Distinction: Police are NOT required to register every complaint as an FIR regardless of its nature. Under the Bharatiya Nagarik Suraksha Sanhita, 2023 (BNSS), mandatory FIR registration applies when information discloses a 'Cognizable Offence'. Non-cognizable matters follow Section 174 BNSS, and purely civil disputes belong in civil/consumer forums."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF4E2600),
                        modifier = Modifier.padding(12.dp)
                    )
                }

                Text(
                    text = if (language == AppLanguage.HINDI) "1. राज्य चुनें (Select State):" else "1. Select State / UT:",
                    style = MaterialTheme.typography.labelLarge
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    VerifiedLegalKnowledgeBase.indianStates.take(7).forEach { st ->
                        FilterChip(
                            selected = selectedState == st,
                            onClick = { selectedState = st },
                            label = { Text(st) }
                        )
                    }
                }

                Text(
                    text = if (language == AppLanguage.HINDI) "2. घटना की प्रकृति चुनें (Nature of Incident):" else "2. Select Nature of Incident:",
                    style = MaterialTheme.typography.labelLarge
                )
                incidentCategories.forEach { cat ->
                    FilterChip(
                        selected = incidentCategory == cat,
                        onClick = { incidentCategory = cat },
                        label = { Text(cat) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Dynamic Legal Assessment based on selection
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val explanationTitle = when {
                            incidentCategory.contains("Cognizable)") ->
                                if (language == AppLanguage.HINDI) "संज्ञेय अपराध (Cognizable Offence) – BNSS धारा 173(1) और 173(4)"
                                else "Cognizable Offence – BNSS Section 173(1), 173(3) & 173(4)"
                            incidentCategory.contains("Non-Cognizable") ->
                                if (language == AppLanguage.HINDI) "असंज्ञेय मामला (Non-Cognizable) – BNSS धारा 174"
                                else "Non-Cognizable Matter – BNSS Section 174"
                            incidentCategory.contains("Zero FIR") ->
                                if (language == AppLanguage.HINDI) "जीरो एफआईआर का अधिकार (Zero FIR) – BNSS धारा 173(1)"
                                else "Zero FIR Right – BNSS Section 173(1)"
                            else ->
                                if (language == AppLanguage.HINDI) "सिविल / संविदात्मक विवाद (Civil Dispute)"
                                else "Civil / Contractual Dispute"
                        }

                        val explanationBody = when {
                            incidentCategory.contains("Cognizable)") ->
                                if (language == AppLanguage.HINDI) {
                                    "• BNSS 2023 की धारा 173(1) के अनुसार: यदि सूचना संज्ञेय अपराध प्रकट करती है, तो थाना प्रभारी (SHO) को सूचना दर्ज करनी होती है और धारा 173(2) के तहत शिकायतकर्ता को निःशुल्क FIR प्रति देनी होती है।\n" +
                                        "• अपवाद (धारा 173(3) BNSS): 3 वर्ष या अधिक लेकिन 7 वर्ष से कम सजा वाले कुछ अपराधों में, DSP/ACP की पूर्व अनुमति से पुलिस 14 दिनों के भीतर यह देखने के लिए 'प्रारंभिक जांच' (Preliminary Inquiry) कर सकती है कि प्रथम दृष्टया मामला बनता है या नहीं।\n" +
                                        "• यदि SHO बिना कारण मना करे (धारा 173(4) BNSS): आप अपनी लिखित शिकायत का सार रजिस्टर्ड डाक/स्पीड पोस्ट द्वारा पुलिस अधीक्षक (SP / DCP) को भेज सकते हैं। यदि उसके बाद भी कार्रवाई न हो, तो मजिस्ट्रेट के समक्ष शपथ पत्र (Affidavit) के साथ आवेदन किया जा सकता है।"
                                } else {
                                    "• Section 173(1) & (2) BNSS, 2023: Information disclosing a cognizable offence must be recorded and a copy provided free of cost to the informant/victim.\n" +
                                        "• Preliminary Inquiry Exception (Section 173(3) BNSS): For offences punishable with 3 years or more but less than 7 years, with prior permission of a Deputy Superintendent of Police (DSP/ACP), the officer may conduct a preliminary inquiry within 14 days to ascertain whether a prima facie case exists.\n" +
                                        "• Escalation Route (Section 173(4) BNSS): Any person aggrieved by a refusal on the part of an officer in charge of a police station to record the information may send the substance of such information, in writing and by post, to the Superintendent of Police (SP/DCP). If no action is taken, an application supported by an affidavit may be made to the Judicial Magistrate."
                                }
                            incidentCategory.contains("Non-Cognizable") ->
                                if (language == AppLanguage.HINDI) {
                                    "• BNSS की धारा 174 के तहत: असंज्ञेय अपराध में पुलिस बिना मजिस्ट्रेट के आदेश के सीधे FIR/जांच नहीं करती। पुलिस इसे एनसीआर (NCR - Non-Cognizable Report) रजिस्टर में दर्ज करती है और शिकायतकर्ता को मजिस्ट्रेट के पास जाने के लिए संदर्भित करती है।"
                                } else {
                                    "• Under Section 174 BNSS, 2023: For a non-cognizable offence, the police officer records the substance in the Non-Cognizable Register (NCR) and refers the informant to the Magistrate. A police officer cannot investigate a non-cognizable case without the order of a Magistrate having power to try such case."
                                }
                            incidentCategory.contains("Zero FIR") ->
                                if (language == AppLanguage.HINDI) {
                                    "• BNSS 2023 की धारा 173(1) ने अब स्पष्ट रूप से 'Zero FIR' को कानूनी रूप दे दिया है: संज्ञेय अपराध की सूचना किसी भी पुलिस स्टेशन में दी जा सकती है, चाहे अपराध किसी भी क्षेत्र में हुआ हो। पुलिस यह कहकर मना नहीं कर सकती कि 'यह हमारे थाने के इलाके में नहीं आता'।"
                                } else {
                                    "• Section 173(1) BNSS, 2023 codifies 'Zero FIR': Every information relating to the commission of a cognizable offence, irrespective of the area where the offence is committed, may be given orally or by electronic communication to an officer in charge of a police station. The police cannot refuse registration merely on territorial jurisdiction grounds."
                                }
                            else ->
                                if (language == AppLanguage.HINDI) {
                                    "• यदि मामला विशुद्ध रूप से सिविल लेनदेन, किराया या अनुबंध भंग का है (जिसमें शुरू से धोखाधड़ी का आपराधिक इरादा न हो), तो पुलिस इसमें FIR दर्ज नहीं करती। इसके लिए आपको सिविल न्यायालय, उपभोक्ता आयोग या DLSA प्री-लिटिगेशन मध्यस्थता का उपयोग करना चाहिए।"
                                } else {
                                    "• Purely civil breaches of contract, unpaid loans, or tenancy deposit disagreements (without dishonest criminal inducement at inception) are not criminal offences. Rather than filing a police FIR, use Nyaya Sahayak's Refund Recovery, Consumer Commission, or DLSA Pre-Litigation Mediation tools."
                                }
                        }

                        Text(
                            text = explanationTitle,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = explanationBody,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                HorizontalDivider()

                // SP / DCP Section 173(4) BNSS Written Representation Generator
                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "पुलिस अधीक्षक (SP/DCP) को धारा 173(4) BNSS के तहत डाक द्वारा भेजने हेतु प्रार्थना पत्र तैयार करें:"
                    } else {
                        "Prepare Written Escalation to SP / DCP under Section 173(4) BNSS, 2023:"
                    },
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = complainantName,
                    onValueChange = { complainantName = it },
                    label = { Text(if (language == AppLanguage.HINDI) "आपका नाम और पता" else "Your Name & Address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = policeStationName,
                        onValueChange = { policeStationName = it },
                        label = { Text(if (language == AppLanguage.HINDI) "पुलिस स्टेशन का नाम" else "Police Station Name") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = refusalDate,
                        onValueChange = { refusalDate = it },
                        label = { Text(if (language == AppLanguage.HINDI) "अस्वीकृति की तारीख" else "Date Visited Station") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }
                OutlinedTextField(
                    value = incidentSummary,
                    onValueChange = { incidentSummary = it },
                    label = { Text(if (language == AppLanguage.HINDI) "घटना का संक्षिप्त विवरण" else "Brief Substance of Offence / Incident") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        val today = SimpleDateFormat("dd-MM-yyyy", Locale.US).format(Date())
                        spDraftText = """
BY REGISTERED POST / SPEED POST (WITH ACKNOWLEDGEMENT DUE)
Date: $today

TO:
The Superintendent of Police (SP) / Deputy Commissioner of Police (DCP),
District Police Headquarters, $selectedState

FROM:
${complainantName.ifBlank { "[Applicant Name & Address]" }}

SUBJECT: REPRESENTATION UNDER SECTION 173(4) OF THE BHARATIYA NAGARIK SURAKSHA SANHITA, 2023 (BNSS) REGARDING NON-REGISTRATION OF INFORMATION AT POLICE STATION ${policeStationName.ifBlank { "[STATION NAME]" }}.

Respected Sir/Madam,

1. That I approached Police Station ${policeStationName.ifBlank { "[Police Station]" }} on ${refusalDate.ifBlank { "[Date]" }} to submit written information regarding the incident summarized below.
2. Substance of Information / Incident:
   ${incidentSummary.ifBlank { "[Insert factual chronological account of the incident]" }}
3. That the Officer-in-Charge did not record the information / issue an acknowledgement receipt.
4. I am enclosing a signed copy of my original complaint herewith under Section 173(4) of the Bharatiya Nagarik Suraksha Sanhita, 2023, and respectfully request that appropriate legal action / investigation be directed in accordance with law.

Enclosures:
1. Signed copy of original written complaint dated ${refusalDate.ifBlank { today }}.
2. Supporting documents/evidence, if any.

Yours faithfully,
_______________________
(${complainantName.ifBlank { "Signature of Informant" }})
                        """.trimIndent()
                    },
                    modifier = Modifier.fillMaxWidth().testTag("generate_sp_letter_button")
                ) {
                    Text(
                        if (language == AppLanguage.HINDI) "धारा 173(4) BNSS का पत्र तैयार करें"
                        else "Generate Sec 173(4) BNSS Representation to SP/DCP"
                    )
                }

                if (spDraftText.isNotBlank()) {
                    OutlinedTextField(
                        value = spDraftText,
                        onValueChange = { spDraftText = it },
                        textStyle = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                        minLines = 10,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(
                            onClick = {
                                copyTextToClipboard(context, "BNSS 173(4)", spDraftText, "Copied to clipboard!")
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (language == AppLanguage.HINDI) "कॉपी करें" else "Copy")
                        }
                        Button(
                            onClick = {
                                shareOrExportDocument(context, "BNSS Sec 173(4) Representation", spDraftText)
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (language == AppLanguage.HINDI) "साझा/डाउनलोड" else "Export")
                        }
                    }
                }
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}

@Composable
fun DeadlineTrackerScreen(
    language: AppLanguage,
    deadlines: List<LegalDeadlineEntity>,
    onAddDeadline: (String, String, String, String, Boolean, String, String) -> Unit,
    onToggleCompleted: (Int, Boolean) -> Unit,
    onDeleteDeadline: (Int) -> Unit
) {
    val scrollState = rememberScrollState()

    var caseTitle by remember { mutableStateOf("") }
    var caseType by remember { mutableStateOf("Consumer Complaint") }
    var incidentOrOrderDate by remember { mutableStateOf("") }
    var establishedDeadlineDate by remember { mutableStateOf("") }
    var isConfirmedByLawyer by remember { mutableStateOf(false) }
    var documentsChecklist by remember { mutableStateOf("") }
    var reminderNote by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Strict Limitation Period Advisory (Prompt Section 12)
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, Color(0xFFE65100).copy(alpha = 0.45f))
        ) {
            Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.WarningAmber, contentDescription = null, tint = Color(0xFFE65100))
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "महत्वपूर्ण समय-सीमा चेतावनी (Limitation Warning): यह ट्रैकर अपने आप कानूनी परिसीमा काल (Limitation Period) की गणना या कल्पना नहीं करता। जहां सटीक कानूनी अंतिम तिथि अनिश्चित हो, वहां तुरंत किसी योग्य वकील या DLSA अधिवक्ता से इसकी पुष्टि करें।"
                    } else {
                        "Statutory Limitation Advisory: This tracker records your dates and reminders—it does NOT automatically invent or calculate statutory limitation periods (which depend on the Limitation Act, 1963, exclusion of time, and specific statutes). Where a legal deadline is uncertain, verify it promptly with a qualified lawyer."
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF4E2600)
                )
            }
        }

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
                        "नई कानूनी तिथि / रिमाइंडर जोड़ें (Add Legal Deadline)"
                    } else {
                        "Record New Legal Date / Deadline"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = caseTitle,
                    onValueChange = { caseTitle = it; errorMsg = null },
                    label = { Text(if (language == AppLanguage.HINDI) "मामले या नोटिस का शीर्षक *" else "Case or Matter Title *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("deadline_title_input")
                )

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = caseType,
                        onValueChange = { caseType = it },
                        label = { Text(if (language == AppLanguage.HINDI) "मामले का प्रकार" else "Case / Issue Type") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = incidentOrOrderDate,
                        onValueChange = { incidentOrOrderDate = it; errorMsg = null },
                        label = { Text(if (language == AppLanguage.HINDI) "नोटिस/आदेश तिथि *" else "Notice / Order Date *") },
                        placeholder = { Text("YYYY-MM-DD") },
                        singleLine = true,
                        modifier = Modifier.weight(1f).testTag("deadline_order_date_input")
                    )
                }

                OutlinedTextField(
                    value = establishedDeadlineDate,
                    onValueChange = { establishedDeadlineDate = it },
                    label = {
                        Text(
                            if (language == AppLanguage.HINDI) "निर्धारित अंतिम तिथि (यदि ज्ञात हो, अन्यथा खाली छोड़ें)"
                            else "Established Deadline Date (if known; leave blank if uncertain)"
                        )
                    },
                    placeholder = { Text("YYYY-MM-DD or Unverified") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isConfirmedByLawyer,
                        onCheckedChange = { isConfirmedByLawyer = it }
                    )
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "यह अंतिम तिथि कोर्ट के आदेश में लिखी है या वकील द्वारा सत्यापित है"
                        } else {
                            "This deadline is explicitly stated on a court/authority order or verified by a lawyer"
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedTextField(
                    value = documentsChecklist,
                    onValueChange = { documentsChecklist = it },
                    label = { Text(if (language == AppLanguage.HINDI) "तैयार रखने योग्य दस्तावेज (Documents Checklist)" else "Documents to Prepare") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = reminderNote,
                    onValueChange = { reminderNote = it },
                    label = { Text(if (language == AppLanguage.HINDI) "रिमाइंडर नोट (Reminder Note)" else "Reminder / Action Note") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                if (errorMsg != null) {
                    Text(errorMsg!!, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                }

                Button(
                    onClick = {
                        if (caseTitle.isBlank() || incidentOrOrderDate.isBlank()) {
                            errorMsg = if (language == AppLanguage.HINDI) {
                                "कृपया मामले का शीर्षक और संबंधित नोटिस/घटना की तारीख भरें।"
                            } else {
                                "Please enter the Case Title and the Relevant / Notice Date."
                            }
                            return@Button
                        }
                        onAddDeadline(
                            caseTitle,
                            caseType,
                            incidentOrOrderDate,
                            establishedDeadlineDate.ifBlank { "Uncertain – Verify with Lawyer / DLSA Promptly" },
                            isConfirmedByLawyer,
                            documentsChecklist.ifBlank { "Original Notice, ID Proof, Supporting Receipts" },
                            reminderNote.ifBlank { "Verify limitation period with qualified advocate before expiry." }
                        )
                        caseTitle = ""
                        incidentOrOrderDate = ""
                        establishedDeadlineDate = ""
                        documentsChecklist = ""
                        reminderNote = ""
                    },
                    modifier = Modifier.fillMaxWidth().testTag("save_deadline_button")
                ) {
                    Icon(Icons.Default.EventNote, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(if (language == AppLanguage.HINDI) "समय-सीमा ट्रैकर में जोड़ें" else "Save to Deadline Tracker")
                }
            }
        }

        Text(
            text = if (language == AppLanguage.HINDI) {
                "आपकी दर्ज की गई कानूनी तिथियां (${deadlines.size})"
            } else {
                "Upcoming Dates & Recorded Deadlines (${deadlines.size})"
            },
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        deadlines.forEach { d ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(
                    1.dp,
                    if (d.isDeadlineConfirmedByLawyer) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                    else Color(0xFFE65100).copy(alpha = 0.4f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                            Checkbox(
                                checked = d.isCompleted,
                                onCheckedChange = { onToggleCompleted(d.id, it) }
                            )
                            Text(
                                text = d.caseTitle,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                textDecoration = if (d.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                            )
                        }
                        IconButton(onClick = { onDeleteDeadline(d.id) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                        }
                    }

                    Text(
                        text = "${d.caseType} • Notice/Order Date: ${d.incidentOrOrderDate}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Target Deadline: ${d.establishedDeadlineDate}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    if (!d.isDeadlineConfirmedByLawyer) {
                        Text(
                            text = if (language == AppLanguage.HINDI) {
                                "⚠ वकील द्वारा असत्यापित: कृपया सटीक परिसीमा काल (Limitation) की पुष्टि किसी योग्य वकील से तुरंत करें।"
                            } else {
                                "⚠ Unverified Limitation: Please confirm the exact statutory limitation date with a qualified lawyer promptly."
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFE65100)
                        )
                    }
                    Text(
                        text = "Documents: ${d.documentsChecklist}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Note: ${d.reminderNote}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LegalAidMapDirectoryScreen(
    language: AppLanguage
) {
    val scrollState = rememberScrollState()
    var selectedState by remember { mutableStateOf("All States / National") }
    var selectedCategory by remember { mutableStateOf("ALL") }
    var searchQuery by remember { mutableStateOf("") }
    var showDemoEntries by remember { mutableStateOf(false) }

    val categories = listOf(
        "ALL" to (if (language == AppLanguage.HINDI) "सभी सत्यापित केंद्र" else "All Verified Centres"),
        "NALSA" to "NALSA (National)",
        "SLSA" to "SLSA / DLSA",
        "MEDIATION" to (if (language == AppLanguage.HINDI) "मध्यस्थता केंद्र" else "Mediation Centres"),
        "LAW_SCHOOL_CLINIC" to (if (language == AppLanguage.HINDI) "लॉ यूनिवर्सिटी क्लिनिक" else "Law School Clinics")
    )

    val filteredEntries = remember(selectedState, selectedCategory, searchQuery, showDemoEntries) {
        VerifiedLegalKnowledgeBase.verifiedDirectoryEntries.filter { entry ->
            val stateOk = selectedState == "All States / National" ||
                entry.state.equals(selectedState, ignoreCase = true) ||
                entry.category == "NALSA"
            val catOk = selectedCategory == "ALL" || entry.category == selectedCategory
            val demoOk = if (showDemoEntries) true else entry.isVerifiedOfficial
            val q = searchQuery.trim().lowercase()
            val queryOk = q.isEmpty() ||
                entry.name.en.lowercase().contains(q) ||
                entry.name.hi.contains(q) ||
                entry.city.lowercase().contains(q) ||
                entry.district.lowercase().contains(q) ||
                entry.state.lowercase().contains(q)
            stateOk && catOk && demoOk && queryOk
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
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "विधिक सहायता निर्देशिका (Neighbourhood Legal Aid Directory)"
                    } else {
                        "Neighbourhood Legal Aid & ADR Directory"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "पारदर्शिता नोट: यह निर्देशिका आपकी लोकेशन ट्रैक किए बिना आधिकारिक रूप से सत्यापित NALSA, SLSA, मध्यस्थता केंद्र और राष्ट्रीय विधि विश्वविद्यालय क्लिनिक दिखाती है।"
                    } else {
                        "Privacy-First Directory: Displays verified statutory Legal Services Authorities (NALSA, SLSA, DLSA), Court Mediation Centres, and Law University Legal Aid Clinics without tracking your GPS location."
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = {
                        Text(
                            if (language == AppLanguage.HINDI) "शहर, राज्य या प्राधिकरण खोजें (जैसे: Delhi, Mumbai, Bengaluru)"
                            else "Search by City, State, or Authority (e.g., Delhi, Mumbai, Lucknow)"
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("directory_search_input")
                )

                Text(
                    text = if (language == AppLanguage.HINDI) "राज्य द्वारा फ़िल्टर करें:" else "Filter by State:",
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

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    categories.forEach { (code, label) ->
                        FilterChip(
                            selected = selectedCategory == code,
                            onClick = { selectedCategory = code },
                            label = { Text(label) }
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "असत्यापित डेमो वकील प्रोफ़ाइल भी दिखाएं"
                        } else {
                            "Include clearly-labelled Fictional Demo Lawyer Profiles"
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                    Switch(checked = showDemoEntries, onCheckedChange = { showDemoEntries = it })
                }
            }
        }

        if (filteredEntries.isEmpty()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "इस खोज के लिए कोई स्थानीय प्रविष्टि नहीं मिली। कृपया राष्ट्रीय विधिक सेवा प्राधिकरण (NALSA) के टोल-फ्री नंबर 15100 पर कॉल करें।"
                    } else {
                        "No matching entry found for this filter. Please call the pan-India NALSA Legal Aid Helpline at 15100 or visit your District Court Front Office."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            filteredEntries.forEach { entry ->
                DirectoryEntryCard(entry = entry, language = language)
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}

@Composable
fun PrivacyAndSourcesScreen(
    language: AppLanguage,
    onDeleteAllData: () -> Unit
) {
    val scrollState = rememberScrollState()
    var deletionConfirmedMessage by remember { mutableStateOf<String?>(null) }

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
                        "कानूनी विश्वसनीयता, स्रोत रखरखाव और डेटा गोपनीयता"
                    } else {
                        "Legal Reliability, Directory Maintenance & Privacy"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "1. आधिकारिक भारतीय कानूनी स्रोतों का रखरखाव:\n" +
                            "• भारतीय न्याय संहिता (BNS), 2023 और भारतीय नागरिक सुरक्षा संहिता (BNSS), 2023 (1 जुलाई 2024 से प्रभावी) के प्रावधान इंडिया कोड (indiacode.nic.in) और गृह मंत्रालय से सत्यापित हैं।\n" +
                            "• उपभोक्ता संरक्षण अधिनियम, 2019 और ई-कॉमर्स नियम, 2020 उपभोक्ता मामले विभाग (consumeraffairs.nic.in / e-jagriti.gov.in) से सत्यापित हैं।\n" +
                            "• निःशुल्क विधिक सहायता पात्रता (धारा 12) विधिक सेवा प्राधिकरण अधिनियम, 1987 और NALSA (nalsa.gov.in) से सत्यापित है।\n\n" +
                            "2. वकील और विधिक सहायता निर्देशिका का रखरखाव:\n" +
                            "• ऐप में केवल सत्यापित वैधानिक प्राधिकरण (NALSA, SLSA, उच्च न्यायालय मध्यस्थता केंद्र, और राष्ट्रीय विधि विश्वविद्यालय क्लिनिक) को 'VERIFIED' बैज दिया गया है।\n" +
                            "• कोई भी नमूना वकील प्रोफ़ाइल '[DEMO ENTRY]' और 'UNVERIFIED' के रूप में स्पष्ट रूप से अलग रखी गई है ताकि उपयोगकर्ता कभी भ्रमित न हों।\n\n" +
                            "3. डेटा सुरक्षा और गोपनीयता (DPDP सिद्धांत):\n" +
                            "• आपकी शिकायतें, रिफंड नोटिस और समय-सीमाएं केवल आपके फोन के स्थानीय डेटाबेस (Room SQLite) में सहेजी जाती हैं।\n" +
                            "• किसी अन्य उपयोगकर्ता को आपकी शिकायत या दस्तावेज कभी दिखाई नहीं देते।\n" +
                            "• आप नीचे दिए गए बटन से किसी भी समय अपना सारा सहेजा गया डेटा तुरंत मिटा सकते हैं।"
                    } else {
                        "1. How Current Legal Information is Maintained:\n" +
                            "• Criminal procedure and penal references reflect the enacted Bharatiya Nagarik Suraksha Sanhita (BNSS), 2023 (Sections 173 & 174) and Bharatiya Nyaya Sanhita (BNS), 2023 (in force from July 1, 2024), cross-verified against India Code (indiacode.nic.in).\n" +
                            "• Consumer remedies reference the Consumer Protection Act, 2019, Consumer Protection (E-Commerce) Rules, 2020, National Consumer Helpline (1915), and e-Jagriti.\n" +
                            "• Free legal aid eligibility reflects Section 12 of the Legal Services Authorities Act, 1987 and NALSA (nalsa.gov.in).\n\n" +
                            "2. How Real Legal Aid Directories & Referrals are Maintained:\n" +
                            "• Only statutory authorities (NALSA, State Legal Services Authorities, High Court Mediation Centres, and statutory NLU Legal Aid Clinics) carry the VERIFIED badge with official addresses and toll-free numbers.\n" +
                            "• Sample advocate cards for UI demonstration are strictly isolated behind a toggle and marked '[DEMO ENTRY] – UNVERIFIED'.\n\n" +
                            "3. Data Privacy & Local Storage Protection:\n" +
                            "• No eKYC or Aadhaar is collected.\n" +
                            "• All drafted complaints, refund notices, lawyer intake records, and deadlines are stored privately on your device in an isolated Room database.\n" +
                            "• You have one-tap control below to permanently erase all personal records stored in the app."
                    },
                    style = MaterialTheme.typography.bodySmall
                )

                Button(
                    onClick = {
                        onDeleteAllData()
                        deletionConfirmedMessage = if (language == AppLanguage.HINDI) {
                            "✓ आपके डिवाइस से सभी सहेजी गई शिकायतें, रिफंड मामले, समय-सीमाएं और अनुरोध स्थायी रूप से हटा दिए गए हैं।"
                        } else {
                            "✓ All saved complaints, lawyer referrals, refund notices, and deadlines have been permanently erased from this device."
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier.fillMaxWidth().testTag("delete_all_personal_data_button")
                ) {
                    Icon(Icons.Default.DeleteForever, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        if (language == AppLanguage.HINDI) "मेरा सारा सहेजा गया डेटा मिटाएं (Delete All Saved Data)"
                        else "Erase All My Saved Complaints & Records"
                    )
                }

                if (deletionConfirmedMessage != null) {
                    Surface(
                        color = Color(0xFFD2F5E3),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = deletionConfirmedMessage!!,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF04291B),
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}
