package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.AppLanguage
import com.example.data.repository.VerifiedLegalKnowledgeBase
import com.example.ui.components.LegalInformationDisclaimerBanner
import com.example.ui.components.dialHelplineNumber
import com.example.ui.components.openOfficialUrl

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FindRemedyScreen(
    language: AppLanguage,
    aiLoading: Boolean,
    aiResponseText: String?,
    aiErrorNote: String?,
    onAskAi: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val categories = VerifiedLegalKnowledgeBase.remedyCategories
    var selectedCategory by remember { mutableStateOf(categories.first()) }
    var userProblemInput by remember { mutableStateOf("") }

    // Interactive follow-up questions state
    var hasWrittenEvidence by remember { mutableStateOf(true) }
    var alreadyContactedOppositeParty by remember { mutableStateOf(false) }

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
                        "मेरा कानूनी उपाय खोजें (Find My Remedy)"
                    } else {
                        "Find My Remedy – Guided Legal Problem Solver"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "अपनी समस्या का प्रकार चुनें या साधारण भाषा में अपनी समस्या लिखें।"
                    } else {
                        "Select a problem category or describe your issue in everyday language to view verified Indian legal remedies, required documents, and step-by-step actions."
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Category Chips
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    categories.forEach { cat ->
                        FilterChip(
                            selected = selectedCategory.categoryId == cat.categoryId,
                            onClick = { selectedCategory = cat },
                            label = { Text(cat.title.get(language)) },
                            modifier = Modifier.testTag("remedy_cat_${cat.categoryId}")
                        )
                    }
                }

                HorizontalDivider()

                // Interactive Follow-up Questions
                Text(
                    text = if (language == AppLanguage.HINDI) "प्रासंगिक अनुवर्ती प्रश्न (Follow-up Questions):" else "Guided Follow-Up Questions:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = hasWrittenEvidence,
                        onClick = { hasWrittenEvidence = !hasWrittenEvidence },
                        label = {
                            Text(
                                if (language == AppLanguage.HINDI) "मेरे पास बिल/रसीद/स्क्रीनशॉट है"
                                else "I have receipts / messages / proof"
                            )
                        }
                    )
                    FilterChip(
                        selected = alreadyContactedOppositeParty,
                        onClick = { alreadyContactedOppositeParty = !alreadyContactedOppositeParty },
                        label = {
                            Text(
                                if (language == AppLanguage.HINDI) "मैंने पहले लिखित शिकायत दी है"
                                else "Already sent written notice"
                            )
                        }
                    )
                }

                // Dynamic Tailored Tip based on follow-up answers
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    val tailoredTip = buildString {
                        if (!hasWrittenEvidence) {
                            append(
                                if (language == AppLanguage.HINDI) "• महत्वपूर्ण: यदि पक्का बिल नहीं है, तो बैंक/UPI स्टेटमेंट, चैट हिस्ट्री या कॉल रिकॉर्डिंग को प्राथमिक साक्ष्य के रूप में सुरक्षित रखें।\n"
                                else "• Tip: Since you don't have a formal invoice, immediately preserve bank/UPI statements, WhatsApp/email logs, and witnesses as corroborative evidence.\n"
                            )
                        }
                        if (!alreadyContactedOppositeParty) {
                            append(
                                if (language == AppLanguage.HINDI) "• अगला कदम: सीधे कोर्ट जाने से पहले विपक्षी को 7-15 दिन का लिखित नोटिस/ईमेल भेजें (आपका मामला मजबूत होगा)।"
                                else "• Recommended First Step: Before approaching a tribunal/court, send a formal written grievance/notice giving 7–15 days to resolve."
                            )
                        } else {
                            append(
                                if (language == AppLanguage.HINDI) "• अगला कदम: चूंकि आप पहले ही संपर्क कर चुके हैं, अब आप सीधे नीचे दिए गए वैधानिक प्राधिकरण (NCH / लोकपाल / आयोग) में शिकायत दर्ज कर सकते हैं।"
                                else "• Recommended Next Step: Since you already notified the opposite party, you can proceed directly to the statutory authority listed below."
                            )
                        }
                    }
                    Text(
                        text = tailoredTip,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }

        // 8 Mandatory Result Sections for Selected Category
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = selectedCategory.title.get(language),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                RemedySectionBlock(
                    number = "1",
                    heading = if (language == AppLanguage.HINDI) "समस्या में क्या शामिल है (What the Problem May Involve)" else "What the Problem May Involve",
                    body = selectedCategory.whatItInvolves.get(language)
                )

                RemedySectionBlock(
                    number = "2",
                    heading = if (language == AppLanguage.HINDI) "संभावित कानूनी उपाय (Possible Legal Remedies)" else "Possible Legal Remedies",
                    body = selectedCategory.possibleRemedies.joinToString("\n") { "• ${it.get(language)}" }
                )

                RemedySectionBlock(
                    number = "3",
                    heading = if (language == AppLanguage.HINDI) "प्रासंगिक कानून और धाराएं (Verified Law & Provisions)" else "Relevant Law & Verified Provisions",
                    body = selectedCategory.verifiedLawProvisions.get(language)
                )

                RemedySectionBlock(
                    number = "4",
                    heading = if (language == AppLanguage.HINDI) "उपयुक्त प्राधिकरण / न्यायालय / मंच (Appropriate Authority or Forum)" else "Appropriate Authority, Court, Tribunal, or Service",
                    body = selectedCategory.appropriateAuthority.get(language)
                )

                RemedySectionBlock(
                    number = "5",
                    heading = if (language == AppLanguage.HINDI) "आवश्यक दस्तावेज (Documents You May Need)" else "Documents You May Need",
                    body = selectedCategory.requiredDocuments.joinToString("\n") { "✓ ${it.get(language)}" }
                )

                RemedySectionBlock(
                    number = "6",
                    heading = if (language == AppLanguage.HINDI) "चरण-दर-चरण प्रक्रिया (How to Proceed Step by Step)" else "How to Proceed Step by Step",
                    body = selectedCategory.stepByStepGuide.joinToString("\n\n") { it.get(language) }
                )

                RemedySectionBlock(
                    number = "7",
                    heading = if (language == AppLanguage.HINDI) "नजदीकी विधिक सहायता (Nearby Legal Aid Services)" else "Nearby Legal Aid Services & Lawyer Suggestions",
                    body = selectedCategory.nearbyAidSuggestion.get(language)
                )

                RemedySectionBlock(
                    number = "8",
                    heading = if (language == AppLanguage.HINDI) "आपातकालीन सहायता (Emergency Assistance)" else "Emergency Assistance",
                    body = selectedCategory.emergencyNote.get(language)
                )

                // Uncertainty & Jurisdiction Disclaimer (Required by Prompt Rule 5)
                Surface(
                    color = Color(0xFFFFF3E0),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color(0xFFE65100).copy(alpha = 0.4f))
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                        Icon(Icons.Default.WarningAmber, contentDescription = null, tint = Color(0xFFE65100), modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = selectedCategory.uncertaintyDisclaimer.get(language),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF4E2600)
                        )
                    }
                }
            }
        }

        // Custom Natural-Language Problem Query Box
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
                        "अपनी विशिष्ट समस्या साधारण शब्दों में लिखें"
                    } else {
                        "Describe Your Specific Situation in Ordinary Language"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = userProblemInput,
                    onValueChange = { userProblemInput = it },
                    label = {
                        Text(
                            if (language == AppLanguage.HINDI) "उदाहरण: मैंने ऑनलाइन लैपटॉप मंगवाया लेकिन टूटा हुआ निकला..."
                            else "e.g., I paid a contractor ₹25,000 for home repair and he stopped answering calls..."
                        )
                    },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth().testTag("find_remedy_custom_input")
                )
                Button(
                    onClick = {
                        // Also auto-match local category if keywords match
                        val lower = userProblemInput.lowercase()
                        when {
                            lower.contains("salary") || lower.contains("employer") || lower.contains("वेतन") ->
                                categories.find { it.categoryId == "employment" }?.let { selectedCategory = it }
                            lower.contains("fraud") || lower.contains("upi") || lower.contains("cyber") || lower.contains("ठगी") ->
                                categories.find { it.categoryId == "cybercrime" }?.let { selectedCategory = it }
                            lower.contains("landlord") || lower.contains("rent") || lower.contains("deposit") || lower.contains("किराया") ->
                                categories.find { it.categoryId == "rental_property" }?.let { selectedCategory = it }
                            lower.contains("bank") || lower.contains("atm") || lower.contains("बैंक") ->
                                categories.find { it.categoryId == "banking_payment" }?.let { selectedCategory = it }
                            lower.contains("refund") || lower.contains("shop") || lower.contains("रिफंड") ->
                                categories.find { it.categoryId == "consumer" }?.let { selectedCategory = it }
                        }
                        onAskAi(userProblemInput)
                    },
                    enabled = !aiLoading && userProblemInput.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (aiLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp, color = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                    } else {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(if (language == AppLanguage.HINDI) "मार्गदर्शन प्राप्त करें" else "Analyze & Show Guidance")
                }

                if (aiResponseText != null) {
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = aiResponseText,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
                if (aiErrorNote != null) {
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "ऊपर आपकी समस्या से मेल खाता सत्यापित भारतीय कानूनी मार्गदर्शन प्रदर्शित किया गया है। ($aiErrorNote)"
                        } else {
                            "Matched your query to the verified statutory category above. ($aiErrorNote)"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}

@Composable
private fun RemedySectionBlock(
    number: String,
    heading: String,
    body: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Text(
            text = "$number. $heading",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = body,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KnowRightsScreen(
    language: AppLanguage,
    aiLoading: Boolean,
    aiResponseText: String?,
    aiErrorNote: String?,
    onAskAi: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val faqs = VerifiedLegalKnowledgeBase.rightsFaqs
    var selectedFaq by remember { mutableStateOf(faqs.first()) }
    var naturalQuestion by remember { mutableStateOf("") }

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
                        "मेरे अधिकार जानें (Know My Rights Assistant)"
                    } else {
                        "Know My Rights – Plain-Language Rights Assistant"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "आपको किसी कानूनी शब्दावली को जानने की आवश्यकता नहीं है। नीचे दिए गए आम प्रश्नों पर टैप करें या अपना प्रश्न लिखें:"
                    } else {
                        "No legal terminology required. Tap a common everyday situation below or type your question naturally:"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Quick Everyday Questions required by Prompt Section 6
                faqs.forEach { faq ->
                    Surface(
                        color = if (selectedFaq.id == faq.id) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(
                            1.dp,
                            if (selectedFaq.id == faq.id) MaterialTheme.colorScheme.primary else Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedFaq = faq }
                            .testTag("rights_faq_${faq.id}")
                    ) {
                        Text(
                            text = "“${faq.sampleQuestion.get(language)}”",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (selectedFaq.id == faq.id) MaterialTheme.colorScheme.onPrimaryContainer
                            else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                OutlinedTextField(
                    value = naturalQuestion,
                    onValueChange = { naturalQuestion = it },
                    label = {
                        Text(
                            if (language == AppLanguage.HINDI) "अपना प्रश्न सरल भाषा में पूछें..."
                            else "Type your question in plain English or Hindi..."
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("rights_natural_query_input")
                )

                Button(
                    onClick = {
                        val qLower = naturalQuestion.lowercase()
                        val matched = faqs.firstOrNull { faq ->
                            faq.keywords.any { kw -> qLower.contains(kw) }
                        }
                        if (matched != null) {
                            selectedFaq = matched
                        }
                        onAskAi(naturalQuestion)
                    },
                    enabled = naturalQuestion.isNotBlank() && !aiLoading,
                    modifier = Modifier.fillMaxWidth().testTag("rights_search_button")
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(if (language == AppLanguage.HINDI) "मेरे अधिकार खोजें" else "Explain My Rights")
                }
            }
        }

        // Display Selected Verified Rights Card
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
                    text = "“${selectedFaq.sampleQuestion.get(language)}”",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                RemedySectionBlock(
                    number = "1",
                    heading = if (language == AppLanguage.HINDI) "सरल व्याख्या (Simple Explanation)" else "Simple Explanation",
                    body = selectedFaq.simpleExplanation.get(language)
                )

                RemedySectionBlock(
                    number = "2",
                    heading = if (language == AppLanguage.HINDI) "लागू होने वाले अधिकार (Rights That May Apply)" else "Rights That May Apply",
                    body = selectedFaq.applicableRights.joinToString("\n") { "• ${it.get(language)}" }
                )

                RemedySectionBlock(
                    number = "3",
                    heading = if (language == AppLanguage.HINDI) "सत्यापित कानूनी स्रोत (Verified Legal Sources)" else "Verified Legal Sources",
                    body = selectedFaq.verifiedLegalSources.get(language)
                )

                RemedySectionBlock(
                    number = "4",
                    heading = if (language == AppLanguage.HINDI) "व्यावहारिक कदम (Practical Steps)" else "Practical Steps",
                    body = selectedFaq.practicalSteps.joinToString("\n") { "→ ${it.get(language)}" }
                )

                RemedySectionBlock(
                    number = "5",
                    heading = if (language == AppLanguage.HINDI) "संभाल कर रखने योग्य दस्तावेज (Documents to Preserve)" else "Documents to Preserve",
                    body = selectedFaq.documentsToPreserve.joinToString("\n") { "✓ ${it.get(language)}" }
                )

                RemedySectionBlock(
                    number = "6",
                    heading = if (language == AppLanguage.HINDI) "कहां शिकायत करें (Where to Complain or Seek Help)" else "Where to Complain or Seek Help",
                    body = selectedFaq.whereToComplain.get(language)
                )

                RemedySectionBlock(
                    number = "7",
                    heading = if (language == AppLanguage.HINDI) "सत्यापित विधिक सहायता संपर्क (Verified Legal Aid Contact)" else "Verified Legal Aid Contact Information",
                    body = selectedFaq.legalAidContact.get(language)
                )
            }
        }

        if (aiResponseText != null) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = if (language == AppLanguage.HINDI) "AI कानूनी सूचना सहायक उत्तर:" else "Additional AI Legal Information Analysis:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = aiResponseText, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KnowRemedyScreen(
    language: AppLanguage
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val authorities = VerifiedLegalKnowledgeBase.authorityComparisonList

    var selectedIssueType by remember { mutableStateOf("Shopping / Refund / Defective Product") }

    val issueOptions = listOf(
        "Shopping / Refund / Defective Product",
        "Need Free Lawyer / Cannot Afford Court Fees",
        "Rent / Family / Neighbour Settlement (Avoid Court)",
        "Electricity / Water / Bank Loan Compromise",
        "Online Fraud / Cyber Scam / Police Crime",
        "Government Department / Pension / Railway Delay"
    )

    val recommendedAuthority = remember(selectedIssueType) {
        when (selectedIssueType) {
            "Shopping / Refund / Defective Product" -> authorities.first { it.id == "nch" }
            "Need Free Lawyer / Cannot Afford Court Fees" -> authorities.first { it.id == "dlsa_slsa" }
            "Rent / Family / Neighbour Settlement (Avoid Court)" -> authorities.first { it.id == "mediation_adr" }
            "Electricity / Water / Bank Loan Compromise" -> authorities.first { it.id == "lok_adalat" }
            "Online Fraud / Cyber Scam / Police Crime" -> authorities.first { it.id == "cyber_police" }
            else -> authorities.first { it.id == "pgportal" }
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
                        "सही मंच और प्राधिकरण चुनें (Know My Remedy)"
                    } else {
                        "Know My Remedy – Guided Authority Selector"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "हर विवाद को कोर्ट ले जाने की आवश्यकता नहीं होती। अपनी समस्या चुनें और जानें कि कौन सा मंच आपके लिए सबसे तेज़ और किफायती है:"
                    } else {
                        "Not every dispute belongs in a courtroom. Select your situation to see the recommended non-court or statutory forum and understand the difference between each authority:"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    issueOptions.forEach { issue ->
                        FilterChip(
                            selected = selectedIssueType == issue,
                            onClick = { selectedIssueType = issue },
                            label = { Text(issue) }
                        )
                    }
                }

                // Recommended Option Highlight
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD2F5E3)),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.5.dp, Color(0xFF0B5D3F))
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF0B5D3F))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (language == AppLanguage.HINDI) "अनुशंसित प्राथमिक मंच (Recommended First Forum):" else "Recommended Primary Forum for Your Issue:",
                                style = MaterialTheme.typography.labelLarge,
                                color = Color(0xFF04291B)
                            )
                        }
                        Text(
                            text = recommendedAuthority.name.get(language),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF04291B)
                        )
                        Text(
                            text = recommendedAuthority.simpleExplanation.get(language),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF04291B)
                        )
                        Text(
                            text = "${if (language == AppLanguage.HINDI) "क्यों चुनें: " else "Why choose this: "}${recommendedAuthority.whenToChoose.get(language)}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF04291B)
                        )
                    }
                }
            }
        }

        // Complete Comparison of All Authorities
        Text(
            text = if (language == AppLanguage.HINDI) {
                "सभी कानूनी मंचों और प्राधिकरणों के बीच अंतर (Comparison of Forums)"
            } else {
                "Simple Comparison of Legal Authorities & ADR Forums"
            },
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        authorities.forEach { auth ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp)
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
                        Text(
                            text = auth.name.get(language),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1f)
                        )
                        Surface(
                            color = if (auth.isCourtProceeding) Color(0xFFFFF3E0) else Color(0xFFE8F5E9),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = if (auth.isCourtProceeding) {
                                    if (language == AppLanguage.HINDI) "अर्ध-न्यायिक ट्रिब्यूनल" else "Quasi-Judicial Tribunal"
                                } else {
                                    if (language == AppLanguage.HINDI) "गैर-अदालती समाधान (Non-Court)" else "Non-Court / ADR"
                                },
                                style = MaterialTheme.typography.labelMedium,
                                color = if (auth.isCourtProceeding) Color(0xFFE65100) else Color(0xFF0B5D3F),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Text(
                        text = auth.simpleExplanation.get(language),
                        style = MaterialTheme.typography.bodySmall
                    )

                    Text(
                        text = "${if (language == AppLanguage.HINDI) "शुल्क और समय: " else "Cost & Timeline: "}${auth.costAndSpeed.get(language)}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Text(
                        text = "${if (language == AppLanguage.HINDI) "कब उपयोग करें: " else "When to use: "}${auth.whenToChoose.get(language)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "${if (language == AppLanguage.HINDI) "संपर्क / पोर्टल: " else "Official Contact: "}${auth.officialPortalOrContact}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        LegalInformationDisclaimerBanner(language = language)
    }
}
