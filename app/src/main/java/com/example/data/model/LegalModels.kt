package com.example.data.model

enum class AppLanguage(val code: String, val displayName: String, val nativeName: String) {
    ENGLISH("en", "English", "English"),
    HINDI("hi", "Hindi", "हिन्दी")
}

data class BilingualText(
    val en: String,
    val hi: String
) {
    fun get(lang: AppLanguage): String = if (lang == AppLanguage.HINDI) hi else en
}

data class LegalDirectoryEntry(
    val id: String,
    val name: BilingualText,
    val category: String, // "NALSA", "SLSA", "DLSA", "MEDIATION", "LAW_SCHOOL_CLINIC", "DEMO_LAWYER"
    val state: String,
    val district: String,
    val city: String,
    val practiceAreas: List<String>,
    val isVerifiedOfficial: Boolean, // true ONLY for verified statutory/official authorities, false for demo lawyers
    val verificationBadgeText: BilingualText,
    val address: BilingualText,
    val contactPhone: String,
    val officialWebsiteOrEmail: String,
    val operatingHours: BilingualText,
    val appointmentInfo: BilingualText
)

data class RemedyGuideItem(
    val categoryId: String,
    val title: BilingualText,
    val subtitle: BilingualText,
    val whatItInvolves: BilingualText,
    val possibleRemedies: List<BilingualText>,
    val verifiedLawProvisions: BilingualText,
    val appropriateAuthority: BilingualText,
    val requiredDocuments: List<BilingualText>,
    val stepByStepGuide: List<BilingualText>,
    val nearbyAidSuggestion: BilingualText,
    val emergencyNote: BilingualText,
    val uncertaintyDisclaimer: BilingualText
)

data class RightsFaqItem(
    val id: String,
    val sampleQuestion: BilingualText,
    val keywords: List<String>,
    val simpleExplanation: BilingualText,
    val applicableRights: List<BilingualText>,
    val verifiedLegalSources: BilingualText,
    val practicalSteps: List<BilingualText>,
    val documentsToPreserve: List<BilingualText>,
    val whereToComplain: BilingualText,
    val legalAidContact: BilingualText
)

data class AuthorityComparisonItem(
    val id: String,
    val name: BilingualText,
    val bestForIssues: List<String>,
    val simpleExplanation: BilingualText,
    val costAndSpeed: BilingualText,
    val isCourtProceeding: Boolean,
    val officialPortalOrContact: String,
    val whenToChoose: BilingualText
)

data class HelplineItem(
    val id: String,
    val number: String,
    val title: BilingualText,
    val purpose: BilingualText,
    val limitationsAndScope: BilingualText,
    val officialPortal: String,
    val isImmediateEmergency: Boolean = false,
    val lastVerifiedNote: BilingualText
)
