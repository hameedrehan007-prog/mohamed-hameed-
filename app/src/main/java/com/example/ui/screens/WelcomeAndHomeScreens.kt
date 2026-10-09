package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalPolice
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.model.AppLanguage
import com.example.ui.components.LegalInformationDisclaimerBanner
import com.example.ui.components.NyayaLogoBadge
import com.example.ui.components.dialHelplineNumber
import com.example.ui.viewmodel.NyayaScreen

@Composable
fun WelcomeAuthScreen(
    language: AppLanguage,
    onToggleLanguage: () -> Unit,
    onEnterGuestDemo: (String) -> Unit
) {
    var mobileInput by remember { mutableStateOf("") }
    var otpInput by remember { mutableStateOf("") }
    var validationMessage by remember { mutableStateOf<String?>(null) }
    var gatewayStatusNotice by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Row with Language Switcher
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(
                onClick = onToggleLanguage,
                modifier = Modifier.testTag("welcome_language_toggle")
            ) {
                Icon(Icons.Default.Language, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(if (language == AppLanguage.ENGLISH) "हिन्दी में बदलें" else "Switch to English")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 540.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NyayaLogoBadge(sizeDp = 72)
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = if (language == AppLanguage.HINDI) "न्याय सहायक (NYAYA SAHAYAK)" else "NYAYA SAHAYAK",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "अपने अधिकार समझें। अपना कानूनी उपाय खोजें।\n(Understand Your Rights. Find Your Remedy.)"
                    } else {
                        "Understand Your Rights. Find Your Remedy."
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(14.dp))

                Image(
                    painter = painterResource(id = R.drawable.img_nyaya_hero_1791549040676),
                    contentDescription = "Nyaya Sahayak Digital Legal Assistance Platform",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(155.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(14.dp))

                // No eKYC Badge
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.VerifiedUser,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (language == AppLanguage.HINDI) {
                                "कोई eKYC या आधार आवश्यक नहीं • निजता सुरक्षित"
                            } else {
                                "No eKYC or Aadhaar Required • Privacy Protected"
                            },
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Mobile Number & OTP Validation Section
                Text(
                    text = if (language == AppLanguage.HINDI) {
                        "मोबाइल नंबर और ओटीपी लॉगिन (Mobile & OTP Login)"
                    } else {
                        "Mobile Number & OTP Login"
                    },
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = mobileInput,
                    onValueChange = { input ->
                        if (input.length <= 10 && input.all { it.isDigit() }) {
                            mobileInput = input
                            validationMessage = null
                        }
                    },
                    label = {
                        Text(
                            if (language == AppLanguage.HINDI) "10-अंकों का भारतीय मोबाइल नंबर (+91)"
                            else "10-Digit Indian Mobile Number (+91)"
                        )
                    },
                    placeholder = { Text("98XXXXXXXX") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("mobile_number_input")
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = otpInput,
                    onValueChange = { input ->
                        if (input.length <= 6 && input.all { it.isDigit() }) {
                            otpInput = input
                            validationMessage = null
                        }
                    },
                    label = {
                        Text(
                            if (language == AppLanguage.HINDI) "6-अंकों का ओटीपी (OTP)"
                            else "6-Digit OTP Code"
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("otp_code_input")
                )

                if (validationMessage != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = validationMessage!!,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (gatewayStatusNotice != null) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = gatewayStatusNotice!!,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            val isValidIndianMobile = mobileInput.length == 10 && mobileInput.first() in listOf('6', '7', '8', '9')
                            if (!isValidIndianMobile) {
                                validationMessage = if (language == AppLanguage.HINDI) {
                                    "कृपया 6, 7, 8 या 9 से शुरू होने वाला मान्य 10-अंकों का भारतीय मोबाइल नंबर दर्ज करें।"
                                } else {
                                    "Please enter a valid 10-digit Indian mobile number starting with 6, 7, 8, or 9."
                                }
                            } else {
                                validationMessage = null
                                gatewayStatusNotice = if (language == AppLanguage.HINDI) {
                                    "पारदर्शिता सूचना: वर्तमान में कोई लाइव टेलीकॉम एसएमएस गेटवे (DLT/SMS API) कॉन्फ़िगर नहीं है। ऐप झूठा एसएमएस भेजने का दावा नहीं करता। कृपया नीचे दिए गए 'अतिथि / डेमो मोड' बटन से सभी कानूनी सेवाओं का उपयोग करें।"
                                } else {
                                    "Transparency Notice: No live Telecom SMS OTP gateway (TRAI DLT / SMS Provider) is configured in this build. Per strict honesty standards, NYAYA SAHAYAK does not pretend an SMS OTP was sent. Please use 'Continue in Guest / Demo Mode' below to access all legal tools."
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("request_otp_button")
                    ) {
                        Text(if (language == AppLanguage.HINDI) "OTP जांचें" else "Request OTP")
                    }

                    Button(
                        onClick = {
                            val isValidIndianMobile = mobileInput.length == 10 && mobileInput.first() in listOf('6', '7', '8', '9')
                            if (!isValidIndianMobile) {
                                validationMessage = if (language == AppLanguage.HINDI) {
                                    "कृपया पहले 10-अंकों का मान्य मोबाइल नंबर दर्ज करें।"
                                } else {
                                    "Please enter a valid 10-digit mobile number first."
                                }
                            } else if (otpInput.length != 6) {
                                validationMessage = if (language == AppLanguage.HINDI) {
                                    "ओटीपी सत्यापन के लिए 6 अंक आवश्यक हैं।"
                                } else {
                                    "OTP verification requires a 6-digit numeric code."
                                }
                            } else {
                                validationMessage = null
                                gatewayStatusNotice = if (language == AppLanguage.HINDI) {
                                    "चूंकि लाइव एसएमएस ओटीपी सर्वर जुड़ा नहीं है, इसलिए ओटीपी को कृत्रिम रूप से सत्यापित नहीं माना गया है। आप नीचे दिए गए 'अतिथि / डेमो मोड' पर क्लिक करके आगे बढ़ सकते हैं।"
                                } else {
                                    "Because a live SMS authentication gateway is not connected, this OTP cannot be verified against a telecom server. Please tap 'Continue in Guest / Demo Mode' below."
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("verify_otp_button")
                    ) {
                        Text(if (language == AppLanguage.HINDI) "OTP सत्यापित करें" else "Verify OTP")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Primary Guest / Demo Mode Entry
                Button(
                    onClick = { onEnterGuestDemo(mobileInput) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("enter_guest_demo_button")
                ) {
                    Text(
                        text = if (language == AppLanguage.HINDI) {
                            "अतिथि / डेमो मोड में प्रवेश करें (Guest / Demo Mode)"
                        } else {
                            "Continue in Guest / Demo Mode"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        LegalInformationDisclaimerBanner(language = language)
    }
}

data class DashboardServiceCardItem(
    val screen: NyayaScreen,
    val titleEn: String,
    val titleHi: String,
    val descEn: String,
    val descHi: String,
    val icon: ImageVector,
    val tag: String,
    val badgeEn: String? = null,
    val badgeHi: String? = null
)

@Composable
fun HomeDashboardScreen(
    language: AppLanguage,
    userSessionLabel: String,
    activeDeadlinesCount: Int,
    savedDraftsCount: Int,
    onNavigate: (NyayaScreen) -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current

    val services = remember(activeDeadlinesCount, savedDraftsCount) {
        listOf(
            DashboardServiceCardItem(
                screen = NyayaScreen.ASK_LAWYER,
                titleEn = "Ask a Lawyer",
                titleHi = "वकील से पूछें (Ask a Lawyer)",
                descEn = "Free legal aid eligibility check & verified DLSA/SLSA directory",
                descHi = "निःशुल्क कानूनी सहायता पात्रता जांच और सत्यापित निर्देशिका",
                icon = Icons.Default.PersonSearch,
                tag = "nav_ask_lawyer"
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.DRAFT_COMPLAINT,
                titleEn = "Draft My Complaint",
                titleHi = "मेरी शिकायत लिखें (Draft Complaint)",
                descEn = "Formal petition-style complaint generator with annexures",
                descHi = "औपचारिक याचिका प्रारूप में शिकायत तैयार करें",
                icon = Icons.Default.Description,
                tag = "nav_draft_complaint",
                badgeEn = if (savedDraftsCount > 0) "$savedDraftsCount Saved" else null,
                badgeHi = if (savedDraftsCount > 0) "$savedDraftsCount सुरक्षित" else null
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.FIND_REMEDY,
                titleEn = "Find My Remedy",
                titleHi = "मेरा उपाय खोजें (Find My Remedy)",
                descEn = "Guided problem solver across 10 everyday legal issues",
                descHi = "आम कानूनी समस्याओं के लिए चरण-दर-चरण समाधान",
                icon = Icons.Default.Lightbulb,
                tag = "nav_find_remedy"
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.KNOW_RIGHTS,
                titleEn = "Know My Rights",
                titleHi = "मेरे अधिकार जानें (Know My Rights)",
                descEn = "Ask questions in plain language with verified legal sources",
                descHi = "सरल भाषा में अपने कानूनी अधिकार और स्रोत जानें",
                icon = Icons.Default.MenuBook,
                tag = "nav_know_rights"
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.KNOW_REMEDY,
                titleEn = "Know My Remedy",
                titleHi = "सही मंच और प्राधिकरण चुनें (Know My Remedy)",
                descEn = "Compare Consumer Commission, NCH, DLSA, Lok Adalat & ADR",
                descHi = "उपभोक्ता आयोग, लोक अदालत, मध्यस्थता और DLSA की तुलना करें",
                icon = Icons.Default.AccountBalance,
                tag = "nav_know_remedy"
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.RESOLVE_DISPUTE,
                titleEn = "Resolve My Dispute (ADR)",
                titleHi = "विवाद सुलझाएं (Mediation & Lok Adalat)",
                descEn = "Mediation, Conciliation, Lok Adalat & Legal Aid Clinic guide",
                descHi = "मध्यस्थता, सुलह, लोक अदालत और विधिक सहायता केंद्र",
                icon = Icons.Default.Handshake,
                tag = "nav_resolve_dispute"
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.REFUND_RECOVERY,
                titleEn = "Refund Recovery",
                titleHi = "रिफंड वसूली सहायता (Refund Recovery)",
                descEn = "Demand notice draft, evidence checklist & escalation steps",
                descHi = "रिफंड मांग नोटिस, साक्ष्य सूची और आगे की प्रक्रिया",
                icon = Icons.Default.CurrencyRupee,
                tag = "nav_refund_recovery"
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.POLICE_REFUSAL,
                titleEn = "Police Refusal Help",
                titleHi = "पुलिस शिकायत अस्वीकृति सहायता",
                descEn = "Guidance under BNSS 2023 Sec 173(1)/(4) & cognizable rules",
                descHi = "BNSS 2023 की धारा 173 के तहत एफआईआर और एसपी/मजिस्ट्रेट प्रक्रिया",
                icon = Icons.Default.LocalPolice,
                tag = "nav_police_refusal"
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.DEADLINE_TRACKER,
                titleEn = "Deadline Tracker",
                titleHi = "कानूनी समय-सीमा ट्रैकर (Deadlines)",
                descEn = "Track notice dates, court orders & document checklists",
                descHi = "नोटिस की तारीखें, आदेश और दस्तावेजों की समय-सीमा ट्रैक करें",
                icon = Icons.Default.EventNote,
                tag = "nav_deadline_tracker",
                badgeEn = "$activeDeadlinesCount Tracked",
                badgeHi = "$activeDeadlinesCount दर्ज"
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.LEGAL_AID_MAP,
                titleEn = "Legal Aid Directory",
                titleHi = "विधिक सहायता निर्देशिका (Legal Aid Map)",
                descEn = "Verified NALSA, SLSA, DLSA, Mediation & Law University Clinics",
                descHi = "सत्यापित राज्य/जिला विधिक सेवा प्राधिकरण और मध्यस्थता केंद्र",
                icon = Icons.Default.LocationOn,
                tag = "nav_legal_aid_map"
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.IMMEDIATE_HELP,
                titleEn = "Immediate Help & Helplines",
                titleHi = "तत्काल सहायता और हेल्पलाइन (112 / 15100)",
                descEn = "Verified emergency numbers: 112, NALSA 15100, Cyber 1930, NCH 1915",
                descHi = "सत्यापित आपातकालीन नंबर: 112, विधिक सहायता 15100, साइबर 1930",
                icon = Icons.Default.PhoneInTalk,
                tag = "nav_immediate_help"
            ),
            DashboardServiceCardItem(
                screen = NyayaScreen.PRIVACY_AND_SOURCES,
                titleEn = "Privacy & Legal Sources",
                titleHi = "गोपनीयता और आधिकारिक कानूनी स्रोत",
                descEn = "Data protection controls, local data deletion & source verification",
                descHi = "डेटा सुरक्षा नियंत्रण, डेटा हटाने का विकल्प और सत्यापित स्रोत",
                icon = Icons.Default.Security,
                tag = "nav_privacy_sources"
            )
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Emergency Quick Strip
        item(span = { GridItemSpan(maxLineSpan) }) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB3261E)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate(NyayaScreen.IMMEDIATE_HELP) }
                    .testTag("emergency_banner_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (language == AppLanguage.HINDI) {
                                "तत्काल खतरे में हैं? आपातकालीन सहायता: 112 | साइबर: 1930 | विधिक: 15100"
                            } else {
                                "Immediate Danger? Call 112 | Cyber Fraud: 1930 | Legal Aid: 15100"
                            },
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = if (language == AppLanguage.HINDI) {
                                "सभी सत्यापित राष्ट्रीय हेल्पलाइन देखने के लिए यहां टैप करें"
                            } else {
                                "Tap to open verified national helplines or dial 112 directly"
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { dialHelplineNumber(context, "112") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFFB3261E)
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(Icons.Default.PhoneInTalk, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("112", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Hero Welcome Banner
        item(span = { GridItemSpan(maxLineSpan) }) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (language == AppLanguage.HINDI) {
                                    "नमस्ते, न्याय सहायक में आपका स्वागत है"
                                } else {
                                    "Welcome to NYAYA SAHAYAK"
                                },
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = if (language == AppLanguage.HINDI) {
                                    "सत्र: $userSessionLabel • अपने अधिकार समझें, सही कानूनी कदम उठाएं"
                                } else {
                                    "Session: $userSessionLabel • Understand Your Rights. Find Your Remedy."
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        OutlinedButton(
                            onClick = onLogout,
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                            modifier = Modifier.testTag("logout_button")
                        ) {
                            Icon(Icons.Default.Logout, contentDescription = "Switch Session", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (language == AppLanguage.HINDI) "लॉगआउट" else "Exit",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }

        // Grid of 12 Functional Modules
        items(services) { service ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onNavigate(service.screen) }
                    .testTag(service.tag)
            ) {
                Column(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = service.icon,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        val badgeText = if (language == AppLanguage.HINDI) service.badgeHi else service.badgeEn
                        if (badgeText != null) {
                            Surface(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = badgeText,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (language == AppLanguage.HINDI) service.titleHi else service.titleEn,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = if (language == AppLanguage.HINDI) service.descHi else service.descEn,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        minLines = 2
                    )
                }
            }
        }

        // Footer Legal Disclaimer
        item(span = { GridItemSpan(maxLineSpan) }) {
            LegalInformationDisclaimerBanner(language = language)
        }
    }
}
