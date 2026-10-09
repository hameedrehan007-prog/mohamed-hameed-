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
import androidx.compose.material.icons.filled.OpenInNew
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.data.local.AdrRequestEntity
import com.example.data.local.RefundCaseEntity
import com.example.data.model.AppLanguage
import com.example.data.repository.VerifiedLegalKnowledgeBase
import com.example.ui.components.LegalInformationDisclaimerBanner
import com.example.ui.components.copyTextToClipboard
import com.example.ui.components.dialHelplineNumber
import com.example.ui.components.openOfficialUrl
import com.example.ui.components.shareOrExportDocument
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ResolveDisputeScreen(
    language: AppLanguage,
    savedAdrRequests: List<AdrRequestEntity>,
    onSubmitAdr: (String, String, String, String, Boolean, (String) -> Unit) -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var disputeAbout by remember { mutableStateOf("") }
    var partiesInvolved by remember { mutableStateOf("") }
    var selectedState by remember { mutableStateOf("Delhi (NCT)") }
    var districtLocation by remember { mutableStateOf("") }
    var preferredAdrMode by remember { mutableStateOf("Mediation (मध्यस्थता)") }
    var needsClinicHelp by remember { mutableStateOf(true) }

    var validationError by remember { mutableStateOf<String?>(null) }
    var generatedReference by remember { mutableStateOf<String?>(null) }

    val adrModes = listOf(
        "Mediation (मध्यस्थता)",
        "Conciliation (सुलह)",
        "Lok Adalat / Pre-Litigation Lok Adalat (लोक अदालत)",
        "Arbitration Guidance (माध्यस्थम्)",
        "Legal Aid Clinic Guidance (विधिक सहायता क्लिनिक)"
    )

    val relevantClinics = remember(selectedState) {
        VerifiedLegalKnowledgeBase.verifiedDirectoryEntries.filter {
            it.isVerifiedOfficial && (it.state == selectedState || it.category == "NALSA" || it.category == "MEDIATION")
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
                        "वैकल्पिक विवाद समाधान (Resolve My Dispute – ADR)"
                    } else {
                        "Resolve My Dispute – ADR & Mediation Module"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "बिना लंबी अदालती लड़ाई के मध्यस्थता (Mediation Act, 2023) या लोक अदालत (Legal Services Authorities Act, 1987) के माध्यम से आपसी सहमति से विवाद सुलझाएं।"
                    } else {
                        "Explore amicable settlement options through Mediation (Mediation Act, 2023), Conciliation, or Pre-Litigation Lok Adalat (Legal Services Authorities Act, 1987)."
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedTextField(
                    value = disputeAbout,
                    onValueChange = { disputeAbout = it; validationError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "1. विवाद किस बारे में है? (What is the dispute about?) *" else "1. What is the dispute about? *") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth().testTag("adr_dispute_about_input")
                )

                OutlinedTextField(
                    value = partiesInvolved,
                    onValueChange = { partiesInvolved = it; validationError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "2. इसमें कौन-कौन शामिल हैं? (Who is involved?) *" else "2. Who is involved (Parties)? *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("adr_parties_input")
                )

                Text(
                    text = if (language == AppLanguage.HINDI) "3. यह कहां हुआ? (State & District) *" else "3. Where did it occur? (State & District) *",
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

                OutlinedTextField(
                    value = districtLocation,
                    onValueChange = { districtLocation = it; validationError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "जिला / शहर (District / City) *" else "District / City *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("adr_district_input")
                )

                Text(
                    text = if (language == AppLanguage.HINDI) "4. आप कौन सा ADR विकल्प पसंद करेंगे?" else "4. Preferred ADR Mechanism:",
                    style = MaterialTheme.typography.labelLarge
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    adrModes.forEach { mode ->
                        FilterChip(
                            selected = preferredAdrMode == mode,
                            onClick = { preferredAdrMode = mode },
                            label = { Text(mode) }
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
                            "5. क्या आपको नजदीकी विधिक सहायता क्लिनिक खोजने में मदद चाहिए?"
                        } else {
                            "5. Would you like help finding a Legal Aid Clinic / ADR Centre?"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Switch(checked = needsClinicHelp, onCheckedChange = { needsClinicHelp = it })
                }

                if (validationError != null) {
                    Text(
                        text = validationError!!,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Button(
                    onClick = {
                        if (disputeAbout.isBlank() || partiesInvolved.isBlank() || districtLocation.isBlank()) {
                            validationError = if (language == AppLanguage.HINDI) {
                                "कृपया विवाद का विवरण, पक्षकारों के नाम और जिला भरें।"
                            } else {
                                "Please enter what the dispute is about, who is involved, and the location."
                            }
                            return@Button
                        }
                        onSubmitAdr(
                            disputeAbout,
                            partiesInvolved,
                            "$districtLocation, $selectedState",
                            preferredAdrMode,
                            needsClinicHelp
                        ) { ref ->
                            generatedReference = ref
                        }
                    },
                    modifier = Modifier.fillMaxWidth().testTag("submit_adr_button")
                ) {
                    Text(
                        if (language == AppLanguage.HINDI) "ADR कार्ययोजना और रेफरल तैयार करें"
                        else "Generate ADR Action Plan & Clinic Guide"
                    )
                }
            }
        }

        // Detailed Next Steps after submission (Never merely "Request recorded")
        if (generatedReference != null) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD2F5E3)),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.5.dp, Color(0xFF0B5D3F))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "ADR मार्गदर्शन और स्थानीय रिकॉर्ड संख्या: $generatedReference"
                        } else {
                            "ADR Action Plan & Local Docket: $generatedReference"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF04291B)
                    )
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "पारदर्शिता नोट: यह ऐप किसी बाहरी न्यायालय सर्वर से सीधे नहीं जुड़ा है। आपका विवरण आपके डिवाइस पर सुरक्षित है। आगे बढ़ने के व्यावहारिक चरण:\n" +
                                "1. अपने जिला न्यायालय परिसर में स्थित 'DLSA फ्रंट ऑफिस' या 'जिला मध्यस्थता केंद्र' (Mediation Centre) में जाएं।\n" +
                                "2. 'प्री-लिटिगेशन मध्यस्थता / लोक अदालत आवेदन फॉर्म' मांगें (इसमें कोई कोर्ट फीस नहीं लगती)।\n" +
                                "3. DLSA दूसरे पक्ष ($partiesInvolved) को समझौता बैठक (Conciliation/Mediation Session) के लिए नोटिस भेजेगा।\n" +
                                "4. यदि दोनों पक्ष सहमत होते हैं, तो लिखित समझौता (Settlement Award) सिविल कोर्ट की डिक्री के समान बाध्यकारी होगा।"
                        } else {
                            "Backend Status Note: Stored locally on device (No live government referral API is connected; you must submit the application directly to the ADR centre as explained below):\n\n" +
                                "How to Contact & Proceed with $preferredAdrMode:\n" +
                                "1. Visit the District Legal Services Authority (DLSA) Front Office or Court-Annexed Mediation Centre at your District Court in $districtLocation, $selectedState.\n" +
                                "2. Submit a one-page Pre-Litigation Mediation / Lok Adalat application naming $partiesInvolved (Zero Court Fee).\n" +
                                "3. The ADR Centre will issue a notice inviting the opposite party for a neutral mediation session.\n" +
                                "4. Any mutually signed Lok Adalat Award or Mediated Settlement Agreement is legally enforceable as a court decree."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF04291B)
                    )
                }
            }
        }

        // Verified ADR Centres & Legal Aid Clinics Directory
        Text(
            text = if (language == AppLanguage.HINDI) {
                "सत्यापित मध्यस्थता केंद्र और विधिक सहायता प्राधिकरण"
            } else {
                "Verified ADR Centres & Legal Aid Clinics"
            },
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        relevantClinics.forEach { entry ->
            DirectoryEntryCard(entry = entry, language = language)
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}

@Composable
fun ImmediateHelpScreen(
    language: AppLanguage
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val helplines = VerifiedLegalKnowledgeBase.verifiedHelplines

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Prominent Immediate Danger Advisory Banner
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFB3261E)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.WarningAmber, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "तत्काल शारीरिक खतरा या आपातकाल?"
                        } else {
                            "IN IMMEDIATE PHYSICAL DANGER?"
                        },
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "यदि आपकी जान, शरीर या सुरक्षा को तत्काल खतरा है, तो कानूनी ड्राफ्ट बनाने में समय न गंवाएं। तुरंत राष्ट्रीय आपातकालीन सेवा 112 पर कॉल करें।"
                    } else {
                        "If you or someone near you faces immediate physical danger, violence, or medical emergency, do not wait—contact National Emergency Response Services (112) immediately."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Button(
                    onClick = { dialHelplineNumber(context, "112") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFB3261E)
                    ),
                    modifier = Modifier.fillMaxWidth().testTag("dial_112_emergency_button")
                ) {
                    Icon(Icons.Default.Call, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (language == AppLanguage.HINDI) "तुरंत 112 पर कॉल करें (Call 112 Now)" else "Dial 112 Emergency Response Now",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Verified Helplines List with Purpose & Limitations
        helplines.forEach { item ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(
                    1.dp,
                    if (item.isImmediateEmergency) Color(0xFFB3261E).copy(alpha = 0.4f)
                    else MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.title.get(language),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = item.lastVerifiedNote.get(language),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Surface(
                            color = if (item.isImmediateEmergency) Color(0xFFF9DEDC) else MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = item.number,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (item.isImmediateEmergency) Color(0xFFB3261E) else MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }

                    Text(
                        text = "${if (language == AppLanguage.HINDI) "उद्देश्य (Purpose): " else "Purpose: "}${item.purpose.get(language)}",
                        style = MaterialTheme.typography.bodySmall
                    )

                    Text(
                        text = "${if (language == AppLanguage.HINDI) "सीमाएं और कार्यक्षेत्र (Scope & Limitations): " else "Limitations & Scope: "}${item.limitationsAndScope.get(language)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(top = 6.dp)
                    ) {
                        Button(
                            onClick = { dialHelplineNumber(context, item.number) },
                            modifier = Modifier.testTag("call_helpline_${item.number}")
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (language == AppLanguage.HINDI) "${item.number} डायल करें" else "Call ${item.number}")
                        }
                        OutlinedButton(
                            onClick = { openOfficialUrl(context, item.officialPortal) }
                        ) {
                            Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (language == AppLanguage.HINDI) "आधिकारिक पोर्टल" else "Official Portal")
                        }
                    }
                }
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}

@Composable
fun RefundRecoveryScreen(
    language: AppLanguage,
    savedRefundCases: List<RefundCaseEntity>,
    onSaveRefundCase: (String, String, String, String, String, String, (String) -> Unit) -> Unit,
    onDeleteRefundCase: (Int) -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var buyerName by remember { mutableStateOf("") }
    var sellerName by remember { mutableStateOf("") }
    var transactionId by remember { mutableStateOf("") }
    var paymentDate by remember { mutableStateOf("") }
    var amountInr by remember { mutableStateOf("") }
    var previousCommunication by remember { mutableStateOf("") }
    var defectOrIssueSummary by remember { mutableStateOf("") }

    // Evidence checklist states
    var hasInvoiceReceipt by remember { mutableStateOf(true) }
    var hasBankUpiProof by remember { mutableStateOf(true) }
    var hasWrittenChatEmail by remember { mutableStateOf(false) }
    var hasProductPhotos by remember { mutableStateOf(false) }

    var editableRefundNotice by remember { mutableStateOf("") }
    var latestRefundRef by remember { mutableStateOf<String?>(null) }
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
                        "रिफंड वसूली सहायता (Refund Recovery Workflow)"
                    } else {
                        "Refund Recovery Assistance Workflow"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                // Mandatory Non-Guarantee Disclaimer (Prompt Section 10)
                Surface(
                    color = Color(0xFFFFF3E0),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color(0xFFE65100).copy(alpha = 0.4f))
                ) {
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "महत्वपूर्ण सूचना: औपचारिक रिफंड नोटिस भेजने या शिकायत दर्ज करने से पैसे की स्वतः वापसी की गारंटी नहीं होती। परिणाम साक्ष्यों, विक्रेता की स्थिति और सक्षम मंच के निर्णय पर निर्भर करता है।"
                        } else {
                            "Important Notice: Sending a formal refund demand notice or lodging a grievance does not guarantee recovery of money. Outcomes depend on your evidence, limitation dates, and adjudication by the competent authority."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF4E2600),
                        modifier = Modifier.padding(12.dp)
                    )
                }

                OutlinedTextField(
                    value = buyerName,
                    onValueChange = { buyerName = it; validationError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "आपका नाम (Consumer Name) *" else "Your Name (Consumer / Buyer) *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("refund_buyer_name")
                )

                OutlinedTextField(
                    value = sellerName,
                    onValueChange = { sellerName = it; validationError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "विक्रेता / कंपनी / ऐप का नाम (Seller / Service Provider) *" else "Seller / Company / Platform Name *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("refund_seller_name")
                )

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = amountInr,
                        onValueChange = { if (it.all { c -> c.isDigit() || c == '.' }) amountInr = it },
                        label = { Text(if (language == AppLanguage.HINDI) "राशि ₹ (Amount) *" else "Amount (₹ INR) *") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.weight(1f).testTag("refund_amount_input")
                    )
                    OutlinedTextField(
                        value = paymentDate,
                        onValueChange = { paymentDate = it },
                        label = { Text(if (language == AppLanguage.HINDI) "भुगतान तिथि *" else "Payment Date *") },
                        placeholder = { Text("YYYY-MM-DD") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = transactionId,
                    onValueChange = { transactionId = it },
                    label = { Text(if (language == AppLanguage.HINDI) "ऑर्डर आईडी / UTR / रसीद संख्या" else "Order ID / UPI UTR / Invoice Number") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = defectOrIssueSummary,
                    onValueChange = { defectOrIssueSummary = it; validationError = null },
                    label = { Text(if (language == AppLanguage.HINDI) "रिफंड मांगने का कारण (खराबी / डिलीवरी न होना / सेवा में कमी) *" else "Reason for Refund (Non-delivery, defective item, cancelled service) *") },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth().testTag("refund_issue_input")
                )

                OutlinedTextField(
                    value = previousCommunication,
                    onValueChange = { previousCommunication = it },
                    label = { Text(if (language == AppLanguage.HINDI) "पूर्व में कस्टमर केयर से हुई बातचीत का विवरण" else "Previous Communication with Seller (Ticket No., Dates)") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                HorizontalDivider()

                // Interactive Evidence Checklist
                Text(
                    text = if (language == AppLanguage.HINDI) "साक्ष्य जांच सूची (Evidence Checklist):" else "Evidence Checklist:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = hasInvoiceReceipt, onCheckedChange = { hasInvoiceReceipt = it })
                    Text(if (language == AppLanguage.HINDI) "पक्का बिल / इनवॉइस / ऑर्डर स्क्रीनशॉट" else "Tax Invoice / Order Confirmation Screenshot", style = MaterialTheme.typography.bodySmall)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = hasBankUpiProof, onCheckedChange = { hasBankUpiProof = it })
                    Text(if (language == AppLanguage.HINDI) "बैंक स्टेटमेंट / UPI ट्रांजेक्शन (UTR) प्रमाण" else "Bank Statement / UPI UTR Payment Proof", style = MaterialTheme.typography.bodySmall)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = hasWrittenChatEmail, onCheckedChange = { hasWrittenChatEmail = it })
                    Text(if (language == AppLanguage.HINDI) "कस्टमर केयर ईमेल / चैट / टिकट नंबर" else "Customer Support Emails / Chat Logs / Ticket ID", style = MaterialTheme.typography.bodySmall)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = hasProductPhotos, onCheckedChange = { hasProductPhotos = it })
                    Text(if (language == AppLanguage.HINDI) "खराब सामान या अनबॉक्सिंग का फोटो/वीडियो" else "Photos / Unboxing Video of Defective Product", style = MaterialTheme.typography.bodySmall)
                }

                if (validationError != null) {
                    Text(validationError!!, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                }

                Button(
                    onClick = {
                        if (buyerName.isBlank() || sellerName.isBlank() || amountInr.isBlank() || defectOrIssueSummary.isBlank()) {
                            validationError = if (language == AppLanguage.HINDI) {
                                "कृपया अपना नाम, विक्रेता का नाम, राशि और समस्या का विवरण भरें।"
                            } else {
                                "Please fill in Buyer Name, Seller Name, Amount, and Reason for Refund."
                            }
                            return@Button
                        }

                        val today = SimpleDateFormat("dd-MM-yyyy", Locale.US).format(Date())
                        val evidenceList = buildList {
                            if (hasInvoiceReceipt) add("Order Confirmation / Tax Invoice")
                            if (hasBankUpiProof) add("Payment Proof (Ref: ${transactionId.ifBlank { "Attached" }})")
                            if (hasWrittenChatEmail) add("Customer Support Correspondence")
                            if (hasProductPhotos) add("Photographic/Video Evidence of Defect")
                        }.joinToString(", ")

                        val draft = """
FORMAL PRE-LITIGATION REFUND DEMAND NOTICE
Date: $today

TO:
The Grievance Officer / Authorized Signatory,
$sellerName

FROM:
$buyerName (Consumer)

SUBJECT: FINAL NOTICE FOR REFUND OF ₹$amountInr REGARDING TRANSACTION / ORDER ID: ${transactionId.ifBlank { "[Order/Txn ID]" }} DATED ${paymentDate.ifBlank { "[Date]" }}

Dear Sir/Madam,

1. TRANSACTION DETAILS:
   I purchased goods/services from $sellerName on ${paymentDate.ifBlank { "[Payment Date]" }} and paid a sum of ₹$amountInr (Transaction / Order Reference: ${transactionId.ifBlank { "As per records" }}).

2. STATEMENT OF DEFECT / DEFICIENCY IN SERVICE:
   $defectOrIssueSummary

3. PRIOR COMMUNICATION:
   ${previousCommunication.ifBlank { "Despite contacting customer support, the promised refund/resolution has not been provided till date." }}

4. STATUTORY CONSUMER RIGHTS:
   Under the Consumer Protection Act, 2019 (and the Consumer Protection (E-Commerce) Rules, 2020 where applicable), withholding refund for defective goods, non-delivery, or deficient service constitutes deficiency in service and unfair trade practice.

5. DEMAND FOR REFUND:
   You are hereby requested to process and credit the refund of ₹$amountInr to my original mode of payment within 7 (seven) days of receipt of this notice, failing which I shall be constrained to escalate the grievance before the National Consumer Helpline (1915) and file a formal complaint before the District Consumer Disputes Redressal Commission via e-Jagriti at your risk as to costs and consequences.

Supporting Evidence Enclosed: $evidenceList

Sincerely,
$buyerName
                        """.trimIndent()

                        editableRefundNotice = draft
                        onSaveRefundCase(
                            sellerName,
                            transactionId,
                            paymentDate,
                            amountInr,
                            defectOrIssueSummary,
                            draft
                        ) { ref ->
                            latestRefundRef = ref
                        }
                    },
                    modifier = Modifier.fillMaxWidth().testTag("generate_refund_notice_button")
                ) {
                    Text(
                        if (language == AppLanguage.HINDI) "रिफंड मांग नोटिस और एस्केलेशन प्लान बनाएं"
                        else "Generate Refund Demand Notice & Escalation Plan"
                    )
                }
            }
        }

        if (editableRefundNotice.isNotBlank()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "रिफंड नोटिस पूर्वावलोकन (Ref: ${latestRefundRef ?: "Draft"})"
                        } else {
                            "Editable Refund Demand Notice (Ref: ${latestRefundRef ?: "Draft"})"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = editableRefundNotice,
                        onValueChange = { editableRefundNotice = it },
                        textStyle = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                        minLines = 12,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedButton(
                            onClick = {
                                copyTextToClipboard(
                                    context,
                                    "Refund Notice",
                                    editableRefundNotice,
                                    if (language == AppLanguage.HINDI) "नोटिस कॉपी किया गया" else "Refund notice copied!"
                                )
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (language == AppLanguage.HINDI) "कॉपी करें" else "Copy Notice")
                        }
                        Button(
                            onClick = {
                                shareOrExportDocument(context, "Refund Demand Notice - $sellerName", editableRefundNotice)
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (language == AppLanguage.HINDI) "साझा/डाउनलोड" else "Share / Download")
                        }
                    }

                    HorizontalDivider()

                    // Steps to follow if the seller does not respond
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "यदि विक्रेता जवाब न दे तो आगे के कदम (Escalation Steps):"
                        } else {
                            "Steps to Follow if the Seller Does Not Respond:"
                        },
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "1. बैंक चार्जबैक (Chargeback): यदि आपने क्रेडिट कार्ड या UPI से भुगतान किया है और सामान नहीं मिला, तो तुरंत अपने बैंक में 'Dispute / Chargeback' दर्ज करें।\n" +
                                "2. राष्ट्रीय उपभोक्ता हेल्पलाइन (1915): 7 दिन बाद https://consumerhelpline.gov.in या 1915 पर यह नोटिस और बिल अपलोड कर शिकायत दर्ज करें।\n" +
                                "3. ई-जागृति (e-Jagriti): यदि फिर भी रिफंड न मिले, तो https://e-jagriti.gov.in पर जिला उपभोक्ता आयोग में ऑनलाइन याचिका दायर करें (₹5 लाख तक के दावों पर ₹0 सरकारी शुल्क)।"
                        } else {
                            "1. Bank Chargeback / Dispute: If paid via Credit Card or UPI for undelivered goods, raise a transaction dispute with your issuing bank immediately.\n" +
                                "2. National Consumer Helpline (NCH 1915): If the seller ignores this notice after 7 days, file a docket on https://consumerhelpline.gov.in or call 1915.\n" +
                                "3. District Consumer Commission (e-Jagriti): File an online complaint at https://e-jagriti.gov.in under Sec 35 of the Consumer Protection Act, 2019 (₹0 filing fee for claims up to ₹5 Lakhs)."
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        if (savedRefundCases.isNotEmpty()) {
            Text(
                text = if (language == AppLanguage.HINDI) "सहेजे गए रिफंड मामले (Saved Refund Cases)" else "Saved Refund Cases",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            savedRefundCases.forEach { c ->
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
                            Text("${c.sellerName} • ₹${c.amountInr}", fontWeight = FontWeight.Bold)
                            Text("${c.referenceNumber} • ${c.paymentDate}", style = MaterialTheme.typography.bodySmall)
                        }
                        IconButton(onClick = { onDeleteRefundCase(c.id) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}
