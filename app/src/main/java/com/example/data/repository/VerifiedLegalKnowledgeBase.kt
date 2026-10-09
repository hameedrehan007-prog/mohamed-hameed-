package com.example.data.repository

import com.example.data.model.AuthorityComparisonItem
import com.example.data.model.BilingualText
import com.example.data.model.HelplineItem
import com.example.data.model.LegalDirectoryEntry
import com.example.data.model.RemedyGuideItem
import com.example.data.model.RightsFaqItem

object VerifiedLegalKnowledgeBase {

    val indianStates = listOf(
        "All States / National",
        "Delhi (NCT)",
        "Maharashtra",
        "Uttar Pradesh",
        "Karnataka",
        "Tamil Nadu",
        "West Bengal",
        "Rajasthan",
        "Bihar",
        "Gujarat",
        "Madhya Pradesh",
        "Telangana",
        "Kerala",
        "Haryana",
        "Punjab"
    )

    val verifiedDirectoryEntries = listOf(
        LegalDirectoryEntry(
            id = "nalsa_hq",
            name = BilingualText(
                "National Legal Services Authority (NALSA)",
                "राष्ट्रीय विधिक सेवा प्राधिकरण (NALSA)"
            ),
            category = "NALSA",
            state = "Delhi (NCT)",
            district = "New Delhi",
            city = "New Delhi",
            practiceAreas = listOf("Free Legal Aid", "Lok Adalat", "Mediation", "Constitutional & Statutory Rights"),
            isVerifiedOfficial = true,
            verificationBadgeText = BilingualText(
                "VERIFIED STATUTORY AUTHORITY (Legal Services Authorities Act, 1987)",
                "सत्यापित वैधानिक प्राधिकरण (विधिक सेवा प्राधिकरण अधिनियम, 1987)"
            ),
            address = BilingualText(
                "B-Block, Ground Floor, Additional Building Complex, Supreme Court of India, New Delhi - 110001",
                "बी-ब्लॉक, भूतल, अतिरिक्त भवन परिसर, भारत का सर्वोच्च न्यायालय, नई दिल्ली - 110001"
            ),
            contactPhone = "15100",
            officialWebsiteOrEmail = "https://nalsa.gov.in | nalsa-dla@nic.in",
            operatingHours = BilingualText(
                "Mon–Sat: 9:30 AM – 5:30 PM (Helpline 15100 active across states)",
                "सोम–शनि: सुबह 9:30 – शाम 5:30 (हेल्पलाइन 15100 राज्यों में सक्रिय)"
            ),
            appointmentInfo = BilingualText(
                "Apply online via NALSA Legal Services Management System (nalsa.gov.in) or walk into Front Office.",
                "NALSA पोर्टल (nalsa.gov.in) पर ऑनलाइन आवेदन करें या फ्रंट ऑफिस में जाएं।"
            )
        ),
        LegalDirectoryEntry(
            id = "dslsa_delhi",
            name = BilingualText(
                "Delhi State Legal Services Authority (DSLSA)",
                "दिल्ली राज्य विधिक सेवा प्राधिकरण (DSLSA)"
            ),
            category = "SLSA",
            state = "Delhi (NCT)",
            district = "Central Delhi",
            city = "New Delhi",
            practiceAreas = listOf("Free Legal Aid", "Family Disputes", "Criminal Defense", "Consumer & Labour Aid", "Victim Compensation"),
            isVerifiedOfficial = true,
            verificationBadgeText = BilingualText(
                "VERIFIED STATE AUTHORITY (Official Govt Record)",
                "सत्यापित राज्य विधिक प्राधिकरण (आधिकारिक सरकारी रिकॉर्ड)"
            ),
            address = BilingualText(
                "Central Office, Rouse Avenue District Court Complex, Pandit Deen Dayal Upadhyaya Marg, New Delhi - 110002",
                "केंद्रीय कार्यालय, राउज एवेन्यू जिला न्यायालय परिसर, दीन दयाल उपाध्याय मार्ग, नई दिल्ली - 110002"
            ),
            contactPhone = "1516",
            officialWebsiteOrEmail = "https://dslsa.org | legalaidwing-dslsa@nic.in",
            operatingHours = BilingualText(
                "24x7 Toll-Free Legal Aid Helpline: 1516 | Office: 10:00 AM – 5:00 PM",
                "24x7 टोल-फ्री हेल्पलाइन: 1516 | कार्यालय: सुबह 10:00 – शाम 5:00"
            ),
            appointmentInfo = BilingualText(
                "Front Offices located in all 7 District Courts of Delhi (Tis Hazari, Karkardooma, Rohini, Dwarka, Saket, Patiala House, Rouse Avenue).",
                "दिल्ली के सभी 7 जिला न्यायालय परिसरों में फ्रंट ऑफिस उपलब्ध हैं।"
            )
        ),
        LegalDirectoryEntry(
            id = "maharashtra_slsa",
            name = BilingualText(
                "Maharashtra State Legal Services Authority (MSLSA)",
                "महाराष्ट्र राज्य विधिक सेवा प्राधिकरण (MSLSA)"
            ),
            category = "SLSA",
            state = "Maharashtra",
            district = "Mumbai City",
            city = "Mumbai",
            practiceAreas = listOf("Free Legal Aid", "Lok Adalat", "Mediation", "Property & Tenancy Aid"),
            isVerifiedOfficial = true,
            verificationBadgeText = BilingualText(
                "VERIFIED STATE AUTHORITY (Official High Court Premises)",
                "सत्यापित राज्य विधिक प्राधिकरण (उच्च न्यायालय परिसर)"
            ),
            address = BilingualText(
                "105, High Court PWD Building, Fort, Mumbai, Maharashtra - 400032",
                "105, हाई कोर्ट पीडब्ल्यूडी भवन, फोर्ट, मुंबई, महाराष्ट्र - 400032"
            ),
            contactPhone = "15100",
            officialWebsiteOrEmail = "https://legalservices.maharashtra.gov.in | mslsa-bhc@nic.in",
            operatingHours = BilingualText(
                "Mon–Sat (except 2nd & 4th Sat): 10:30 AM – 5:30 PM",
                "सोम–शनि (दूसरे और चौथे शनिवार को छोड़कर): सुबह 10:30 – शाम 5:30"
            ),
            appointmentInfo = BilingualText(
                "Contact District Legal Services Authority (DLSA) at Mumbai City Civil Court or respective District Court.",
                "मुंबई सिटी सिविल कोर्ट या संबंधित जिला न्यायालय में DLSA से संपर्क करें।"
            )
        ),
        LegalDirectoryEntry(
            id = "up_slsa",
            name = BilingualText(
                "Uttar Pradesh State Legal Services Authority (UPSLSA)",
                "उत्तर प्रदेश राज्य विधिक सेवा प्राधिकरण (UPSLSA)"
            ),
            category = "SLSA",
            state = "Uttar Pradesh",
            district = "Lucknow",
            city = "Lucknow",
            practiceAreas = listOf("Free Legal Aid", "Lok Adalat", "Family Conciliation", "Revenue & Land Disputes"),
            isVerifiedOfficial = true,
            verificationBadgeText = BilingualText(
                "VERIFIED STATE AUTHORITY (Official Govt Record)",
                "सत्यापित राज्य विधिक प्राधिकरण (आधिकारिक सरकारी रिकॉर्ड)"
            ),
            address = BilingualText(
                "3rd Floor, Jawahar Bhawan Annexe, Ashok Marg, Lucknow, Uttar Pradesh - 226001",
                "तीसरी मंजिल, जवाहर भवन एनेक्सी, अशोक मार्ग, लखनऊ, उत्तर प्रदेश - 226001"
            ),
            contactPhone = "15100",
            officialWebsiteOrEmail = "https://upslsa.up.nic.in | upslsa@nic.in",
            operatingHours = BilingualText(
                "Mon–Sat: 10:00 AM – 5:00 PM",
                "सोम–शनि: सुबह 10:00 – शाम 5:00"
            ),
            appointmentInfo = BilingualText(
                "Approach DLSA Secretary at your District Court परिसर or call NALSA Toll-Free 15100.",
                "अपने जिला न्यायालय परिसर में DLSA सचिव से मिलें या 15100 पर कॉल करें।"
            )
        ),
        LegalDirectoryEntry(
            id = "karnataka_slsa",
            name = BilingualText(
                "Karnataka State Legal Services Authority (KSLSA)",
                "कर्नाटक राज्य विधिक सेवा प्राधिकरण (KSLSA)"
            ),
            category = "SLSA",
            state = "Karnataka",
            district = "Bengaluru Urban",
            city = "Bengaluru",
            practiceAreas = listOf("Free Legal Aid", "Mediation", "Consumer & Employment Disputes", "Lok Adalat"),
            isVerifiedOfficial = true,
            verificationBadgeText = BilingualText(
                "VERIFIED STATE AUTHORITY (Nyaya Degula Building)",
                "सत्यापित राज्य विधिक प्राधिकरण (न्याय देगुला भवन)"
            ),
            address = BilingualText(
                "Nyaya Degula, 1st Floor, H. Siddaiah Road, Bengaluru, Karnataka - 560027",
                "न्याय देगुला, पहली मंजिल, एच. सिद्धैया रोड, बेंगलुरु, कर्नाटक - 560027"
            ),
            contactPhone = "18004252440",
            officialWebsiteOrEmail = "https://kslsa.kar.nic.in | kslsa.blr@nic.in",
            operatingHours = BilingualText(
                "Mon–Sat: 10:00 AM – 5:30 PM (Toll-Free: 1800-425-2440 / 15100)",
                "सोम–शनि: सुबह 10:00 – शाम 5:30 (टोल-फ्री: 1800-425-2440 / 15100)"
            ),
            appointmentInfo = BilingualText(
                "Walk-in legal aid clinic and permanent Lok Adalat available at Nyaya Degula campus.",
                "न्याय देगुला परिसर में वॉक-इन विधिक सहायता क्लिनिक और स्थायी लोक अदालत उपलब्ध है।"
            )
        ),
        LegalDirectoryEntry(
            id = "tn_slsa",
            name = BilingualText(
                "Tamil Nadu State Legal Services Authority (TNSLSA)",
                "तमिलनाडु राज्य विधिक सेवा प्राधिकरण (TNSLSA)"
            ),
            category = "SLSA",
            state = "Tamil Nadu",
            district = "Chennai",
            city = "Chennai",
            practiceAreas = listOf("Free Legal Aid", "Mediation", "Women & Senior Citizen Legal Aid"),
            isVerifiedOfficial = true,
            verificationBadgeText = BilingualText(
                "VERIFIED STATE AUTHORITY (Madras High Court Campus)",
                "सत्यापित राज्य विधिक प्राधिकरण (मद्रास उच्च न्यायालय परिसर)"
            ),
            address = BilingualText(
                "North Fort Road, High Court Campus, Chennai, Tamil Nadu - 600104",
                "नॉर्थ फोर्ट रोड, हाई कोर्ट परिसर, चेन्नई, तमिलनाडु - 600104"
            ),
            contactPhone = "18004252441",
            officialWebsiteOrEmail = "https://tnlegalservices.tn.gov.in | tnslsa@gmail.com",
            operatingHours = BilingualText(
                "Mon–Sat: 10:00 AM – 5:45 PM",
                "सोम–शनि: सुबह 10:00 – शाम 5:45"
            ),
            appointmentInfo = BilingualText(
                "Call Toll-Free 1800-425-2441 or 15100 for panel advocate assignment.",
                "पैनल अधिवक्ता आवंटन के लिए टोल-फ्री 1800-425-2441 या 15100 पर कॉल करें।"
            )
        ),
        LegalDirectoryEntry(
            id = "wb_slsa",
            name = BilingualText(
                "State Legal Services Authority, West Bengal (SLSA WB)",
                "पश्चिम बंगाल राज्य विधिक सेवा प्राधिकरण (SLSA WB)"
            ),
            category = "SLSA",
            state = "West Bengal",
            district = "Kolkata",
            city = "Kolkata",
            practiceAreas = listOf("Free Legal Aid", "Lok Adalat", "Victim Compensation", "Mediation"),
            isVerifiedOfficial = true,
            verificationBadgeText = BilingualText(
                "VERIFIED STATE AUTHORITY (City Civil Court Building)",
                "सत्यापित राज्य विधिक प्राधिकरण (सिटी सिविल कोर्ट भवन)"
            ),
            address = BilingualText(
                "2 & 3, Kiran Sankar Roy Road, 1st Floor, City Civil Court Building, Kolkata - 700001",
                "2 और 3, किरण शंकर रॉय रोड, पहली मंजिल, सिटी सिविल कोर्ट भवन, कोलकाता - 700001"
            ),
            contactPhone = "15100",
            officialWebsiteOrEmail = "https://wbslsa.bangla.gov.in | wbstatelegal@gmail.com",
            operatingHours = BilingualText(
                "Mon–Fri: 10:30 AM – 5:30 PM",
                "सोम–शुक्र: सुबह 10:30 – शाम 5:30"
            ),
            appointmentInfo = BilingualText(
                "Walk-in Front Office at City Civil Court Building or contact District Court DLSA.",
                "सिटी सिविल कोर्ट भवन में फ्रंट ऑफिस पर आएं या जिला न्यायालय DLSA से संपर्क करें।"
            )
        ),
        LegalDirectoryEntry(
            id = "samadhan_delhi_mediation",
            name = BilingualText(
                "SAMADHAN – Delhi High Court Mediation & Conciliation Centre",
                "समाधान – दिल्ली उच्च न्यायालय मध्यस्थता एवं सुलह केंद्र"
            ),
            category = "MEDIATION",
            state = "Delhi (NCT)",
            district = "New Delhi",
            city = "New Delhi",
            practiceAreas = listOf("Pre-Litigation Mediation", "Family Disputes", "Commercial & Property Tenancy", "Workplace Disputes"),
            isVerifiedOfficial = true,
            verificationBadgeText = BilingualText(
                "VERIFIED COURT-ANNEXED MEDIATION CENTRE",
                "सत्यापित न्यायालय-संबद्ध मध्यस्थता केंद्र"
            ),
            address = BilingualText(
                "3rd Floor, Extension Block, Delhi High Court, Sher Shah Road, New Delhi - 110503",
                "तीसरी मंजिल, एक्सटेंशन ब्लॉक, दिल्ली उच्च न्यायालय, शेरशाह रोड, नई दिल्ली - 110503"
            ),
            contactPhone = "01123383260",
            officialWebsiteOrEmail = "https://delhihighcourtmediationcentre.nic.in",
            operatingHours = BilingualText(
                "Mon–Sat: 10:00 AM – 5:00 PM",
                "सोम–शनि: सुबह 10:00 – शाम 5:00"
            ),
            appointmentInfo = BilingualText(
                "Accepts both court-referred cases and pre-litigation mediation requests before filing suit.",
                "मुकदमा दायर करने से पहले प्री-लिटिगेशन मध्यस्थता और न्यायालय द्वारा भेजे गए मामलों को स्वीकार करता है।"
            )
        ),
        LegalDirectoryEntry(
            id = "nlud_legal_aid_clinic",
            name = BilingualText(
                "National Law University Delhi – Legal Services Committee Clinic",
                "नेशनल लॉ यूनिवर्सिटी दिल्ली – विधिक सेवा समिति क्लिनिक"
            ),
            category = "LAW_SCHOOL_CLINIC",
            state = "Delhi (NCT)",
            district = "South West Delhi",
            city = "New Delhi",
            practiceAreas = listOf("Legal Literacy", "Primary Legal Advice", "Drafting Assistance", "DLSA Referral"),
            isVerifiedOfficial = true,
            verificationBadgeText = BilingualText(
                "VERIFIED STATUTORY LAW UNIVERSITY CLINIC",
                "सत्यापित राष्ट्रीय विधि विश्वविद्यालय विधिक क्लिनिक"
            ),
            address = BilingualText(
                "Sector 14, Dwarka, New Delhi - 110078",
                "सेक्टर 14, द्वारका, नई दिल्ली - 110078"
            ),
            contactPhone = "01128034257",
            officialWebsiteOrEmail = "https://nludelhi.ac.in",
            operatingHours = BilingualText(
                "Academic Working Days: 2:00 PM – 5:00 PM",
                "शैक्षणिक कार्य दिवस: दोपहर 2:00 – शाम 5:00"
            ),
            appointmentInfo = BilingualText(
                "Provides free legal guidance and connects eligible citizens to South West DLSA panel lawyers.",
                "निःशुल्क कानूनी मार्गदर्शन प्रदान करता है और पात्र नागरिकों को DLSA पैनल वकीलों से जोड़ता है।"
            )
        ),
        // Clearly marked DEMO / SAMPLE entries as required by Prompt Rule 3 & 15
        LegalDirectoryEntry(
            id = "demo_adv_1",
            name = BilingualText(
                "[DEMO ENTRY] Adv. Rajeshwari Sharma (Sample Profile)",
                "[डेमो प्रविष्टि] अधिवक्ता राजेश्वरी शर्मा (नमूना प्रोफ़ाइल)"
            ),
            category = "DEMO_LAWYER",
            state = "Delhi (NCT)",
            district = "New Delhi",
            city = "New Delhi",
            practiceAreas = listOf("Consumer Complaints", "Family Disputes", "Rental & Property Problems"),
            isVerifiedOfficial = false,
            verificationBadgeText = BilingualText(
                "UNVERIFIED DEMO ENTRY – Fictional Profile for UI Demonstration Only",
                "असत्यापित डेमो प्रविष्टि – केवल यूआई प्रदर्शन के लिए काल्पनिक प्रोफ़ाइल"
            ),
            address = BilingualText(
                "Sample Chamber 00, Demonstration Complex (Not a real office address)",
                "नमूना चैंबर 00, प्रदर्शन परिसर (यह वास्तविक कार्यालय पता नहीं है)"
            ),
            contactPhone = "",
            officialWebsiteOrEmail = "Demo Entry - Use Official DLSA/NALSA above for real legal representation",
            operatingHours = BilingualText(
                "Demo Slot: Mon–Fri 11:00 AM – 4:00 PM",
                "डेमो समय: सोम–शुक्र सुबह 11:00 – शाम 4:00"
            ),
            appointmentInfo = BilingualText(
                "Fictional sample entry to demonstrate how panel lawyers appear when a live Bar Council / DLSA API is connected.",
                "लाइव बार काउंसिल / DLSA API जुड़ने पर पैनल वकील कैसे दिखते हैं, यह दिखाने के लिए काल्पनिक नमूना प्रविष्टि।"
            )
        ),
        LegalDirectoryEntry(
            id = "demo_adv_2",
            name = BilingualText(
                "[DEMO ENTRY] Adv. Vikramaditya Deshmukh (Sample Profile)",
                "[डेमो प्रविष्टि] अधिवक्ता विक्रमादित्य देशमुख (नमूना प्रोफ़ाइल)"
            ),
            category = "DEMO_LAWYER",
            state = "Maharashtra",
            district = "Mumbai City",
            city = "Mumbai",
            practiceAreas = listOf("Cybercrime & Online Fraud", "Banking & Payment Problems", "Employment & Workplace Disputes"),
            isVerifiedOfficial = false,
            verificationBadgeText = BilingualText(
                "UNVERIFIED DEMO ENTRY – Fictional Profile for UI Demonstration Only",
                "असत्यापित डेमो प्रविष्टि – केवल यूआई प्रदर्शन के लिए काल्पनिक प्रोफ़ाइल"
            ),
            address = BilingualText(
                "Sample Office, Fictional Legal Tower (Not a real office address)",
                "नमूना कार्यालय, काल्पनिक लीगल टॉवर (यह वास्तविक पता नहीं है)"
            ),
            contactPhone = "",
            officialWebsiteOrEmail = "Demo Entry - Contact MSLSA (15100) for real verified advocates",
            operatingHours = BilingualText(
                "Demo Slot: Mon–Sat 10:30 AM – 5:00 PM",
                "डेमो समय: सोम–शनि सुबह 10:30 – शाम 5:00"
            ),
            appointmentInfo = BilingualText(
                "Fictional sample entry for competition presentation. Never rely on unverified demo profiles.",
                "प्रतियोगिता प्रस्तुति के लिए काल्पनिक नमूना। वास्तविक सहायता के लिए ऊपर दिए गए सत्यापित SLSA/DLSA चुनें।"
            )
        )
    )

    val remedyCategories = listOf(
        RemedyGuideItem(
            categoryId = "consumer",
            title = BilingualText("Consumer Complaints", "उपभोक्ता शिकायतें (Consumer Complaints)"),
            subtitle = BilingualText("Defective goods, deficient services, unfair trade practices, overcharging", "खराब सामान, अधूरी सेवा, अनुचित व्यापार व्यवहार, अधिक कीमत वसूलना"),
            whatItInvolves = BilingualText(
                "Disputes where you bought a product or paid for a service for personal use (not commercial resale) and faced defects, deficiency in service, false warranty claims, or unfair trade practices.",
                "ऐसे विवाद जहां आपने व्यक्तिगत उपयोग के लिए कोई वस्तु खरीदी या सेवा के लिए भुगतान किया और सामान खराब निकला, सेवा में कमी रही, या अनुचित व्यापार व्यवहार हुआ।"
            ),
            possibleRemedies = listOf(
                BilingualText("Replacement of defective goods or repair of defects", "खराब सामान को बदलना या दोषों को ठीक कराना"),
                BilingualText("Full or partial refund of price paid with interest", "भुगतान की गई राशि का ब्याज सहित पूरा या आंशिक रिफंड"),
                BilingualText("Compensation for loss or mental harassment proved on record", "सिद्ध नुकसान या मानसिक परेशानी के लिए मुआवजा")
            ),
            verifiedLawProvisions = BilingualText(
                "Consumer Protection Act, 2019 (Act No. 35 of 2019): Section 2(7) (Consumer definition), Section 2(9) (Consumer Rights), Section 34–35 (District Consumer Disputes Redressal Commission jurisdiction up to ₹50 Lakhs as per Consumer Protection (Jurisdiction) Rules, 2021), Section 69 (Limitation period: generally 2 years from cause of action). Verified via India Code (indiacode.nic.in).",
                "उपभोक्ता संरक्षण अधिनियम, 2019: धारा 2(7) (उपभोक्ता परिभाषा), धारा 2(9) (उपभोक्ता अधिकार), धारा 34-35 (जिला उपभोक्ता आयोग अधिकार क्षेत्र ₹50 लाख तक), धारा 69 (समय सीमा: सामान्यतः कारण उत्पन्न होने से 2 वर्ष)। स्रोत: इंडिया कोड (indiacode.nic.in)।"
            ),
            appropriateAuthority = BilingualText(
                "1. National Consumer Helpline (NCH - 1915 / consumerhelpline.gov.in) for pre-litigation grievance convergence.\n2. District Consumer Disputes Redressal Commission via e-Jagriti / e-Daakhil portal (e-jagriti.gov.in).",
                "1. राष्ट्रीय उपभोक्ता हेल्पलाइन (1915 / consumerhelpline.gov.in) - मुकदमे से पूर्व समाधान हेतु।\n2. ई-जागृति / ई-दाखिल पोर्टल (e-jagriti.gov.in) के माध्यम से जिला उपभोक्ता विवाद निवारण आयोग।"
            ),
            requiredDocuments = listOf(
                BilingualText("Tax Invoice, receipt, or digital payment proof (UPI/Bank statement)", "पक्का बिल (Invoice), रसीद, या डिजिटल भुगतान प्रमाण (UPI/बैंक स्टेटमेंट)"),
                BilingualText("Warranty/Guarantee card and terms of service", "वारंटी/गारंटी कार्ड और सेवा की शर्तें"),
                BilingualText("Copies of emails, chats, or written notice sent to seller/company", "विक्रेता या कंपनी को भेजे गए ईमेल, चैट या लिखित नोटिस की प्रतियां"),
                BilingualText("Photographs or video showing defect or deficiency", "खराबी या कमी दिखाने वाले फोटो या वीडियो")
            ),
            stepByStepGuide = listOf(
                BilingualText("Step 1: Send a written grievance/notice to the seller's customer care or Nodal Officer giving 7–15 days to resolve.", "चरण 1: विक्रेता के कस्टमर केयर या नोडल अधिकारी को लिखित शिकायत/नोटिस भेजें और समाधान के लिए 7-15 दिन का समय दें।"),
                BilingualText("Step 2: Register a docket on National Consumer Helpline (Call 1915 or consumerhelpline.gov.in).", "चरण 2: राष्ट्रीय उपभोक्ता हेल्पलाइन (1915 या consumerhelpline.gov.in) पर शिकायत दर्ज करें।"),
                BilingualText("Step 3: If unresolved, file a formal complaint online via e-Jagriti portal (e-jagriti.gov.in) before the District Consumer Commission where you reside/work or where the transaction occurred.", "चरण 3: समाधान न होने पर, ई-जागृति पोर्टल (e-jagriti.gov.in) के माध्यम से अपने जिले के उपभोक्ता आयोग में शिकायत दर्ज करें।")
            ),
            nearbyAidSuggestion = BilingualText(
                "Free guidance is available at District Legal Services Authority (DLSA) Front Offices and Law University Consumer Clinics.",
                "जिला विधिक सेवा प्राधिकरण (DLSA) के फ्रंट ऑफिस और विधि विश्वविद्यालय क्लिनिकों में निःशुल्क मार्गदर्शन उपलब्ध है।"
            ),
            emergencyNote = BilingualText(
                "For adulterated food/medicine causing health emergencies, contact medical emergency 112 and Food Safety (FSSAI) / Drug Controller immediately.",
                "मिलावटी भोजन या दवा से स्वास्थ्य आपातकाल होने पर तुरंत 112 और खाद्य सुरक्षा (FSSAI) / ड्रग कंट्रोलर से संपर्क करें।"
            ),
            uncertaintyDisclaimer = BilingualText(
                "Note: Purchases made strictly for commercial resale or business profit are generally excluded unless used exclusively for earning livelihood by self-employment. Limitation periods (usually 2 years under Sec 69) and pecuniary jurisdiction depend on exact payment value and dates.",
                "नोट: व्यावसायिक पुनर्विक्रय के लिए की गई खरीदारी सामान्यतः इसमें शामिल नहीं होती (स्व-रोजगार से आजीविका कमाने के अपवाद को छोड़कर)। समय-सीमा (धारा 69 के तहत सामान्यतः 2 वर्ष) वास्तविक तथ्यों और तारीखों पर निर्भर करती है।"
            )
        ),
        RemedyGuideItem(
            categoryId = "employment",
            title = BilingualText("Employment & Workplace Disputes", "रोजगार और कार्यस्थल विवाद (Employment Disputes)"),
            subtitle = BilingualText("Unpaid salary/wages, wrongful termination, PF/gratuity issues, workplace harassment", "वेतन न मिलना, अनुचित बर्खास्तगी, पीएफ/ग्रेच्युटी समस्या, कार्यस्थल उत्पीड़न"),
            whatItInvolves = BilingualText(
                "Disputes between employees/workers and employers regarding non-payment of earned salary, withholding of relieving letters, provident fund delays, or unsafe/harassing work environments.",
                "कर्मचारियों/श्रमिकों और नियोक्ताओं के बीच अर्जित वेतन का भुगतान न करने, अनुभव पत्र रोकने, पीएफ में देरी या कार्यस्थल पर उत्पीड़न से संबंधित विवाद।"
            ),
            possibleRemedies = listOf(
                BilingualText("Recovery of unpaid earned wages/salary through Labour Commissioner or Samadhan Portal", "श्रम आयुक्त या समाधान पोर्टल के माध्यम से बकाया वेतन की वसूली"),
                BilingualText("Conciliation proceedings before the Labour Officer / Industrial Tribunal (for eligible workmen)", "श्रम अधिकारी / औद्योगिक न्यायाधिकरण के समक्ष सुलह कार्यवाही"),
                BilingualText("Internal Complaint Committee (ICC) / Local Committee inquiry for workplace sexual harassment", "कार्यस्थल पर यौन उत्पीड़न के लिए आंतरिक शिकायत समिति (ICC) / स्थानीय समिति द्वारा जांच")
            ),
            verifiedLawProvisions = BilingualText(
                "• Payment of Wages Act, 1936 / Industrial Disputes Act, 1947 (and corresponding State Shops & Establishments Acts).\n• Sexual Harassment of Women at Workplace (Prevention, Prohibition and Redressal) Act, 2013 (POSH Act).\n• Employees' Provident Funds and Miscellaneous Provisions Act, 1952. Verified via Ministry of Labour & Employment (samadhan.labour.gov.in).",
                "• वेतन भुगतान अधिनियम, 1936 / औद्योगिक विवाद अधिनियम, 1947 और संबंधित राज्य दुकान एवं प्रतिष्ठान अधिनियम।\n• कार्यस्थल पर महिलाओं का यौन उत्पीड़न (निवारण, प्रतिषेध और प्रतितोष) अधिनियम, 2013 (POSH Act)।\n• स्रोत: श्रम एवं रोजगार मंत्रालय (samadhan.labour.gov.in)।"
            ),
            appropriateAuthority = BilingualText(
                "• Ministry of Labour SAMADHAN Portal (samadhan.labour.gov.in) & State Labour Commissioner Office.\n• EPFiGMS Portal (epfigms.gov.in) for Provident Fund grievances.\n• SHe-Box Portal (shebox.wcd.gov.in) for POSH complaints.",
                "• श्रम मंत्रालय का समाधान पोर्टल (samadhan.labour.gov.in) और राज्य श्रम आयुक्त कार्यालय।\n• पीएफ शिकायतों के लिए EPFiGMS पोर्टल (epfigms.gov.in)।\n• कार्यस्थल महिला सुरक्षा के लिए SHe-Box (shebox.wcd.gov.in)।"
            ),
            requiredDocuments = listOf(
                BilingualText("Appointment letter, employment contract, or ID card", "नियुक्ति पत्र, रोजगार अनुबंध या पहचान पत्र"),
                BilingualText("Salary slips and bank statements showing last salary credited", "वेतन पर्ची (Salary Slips) और बैंक स्टेटमेंट"),
                BilingualText("Copies of attendance records, emails, or termination notice", "उपस्थिति रिकॉर्ड, ईमेल या बर्खास्तगी नोटिस की प्रतियां")
            ),
            stepByStepGuide = listOf(
                BilingualText("Step 1: Send a formal written representation/demand notice to HR and Management requesting clear settlement of dues.", "चरण 1: एचआर और प्रबंधन को बकाया राशि के निपटान के लिए औपचारिक लिखित मांग पत्र भेजें।"),
                BilingualText("Step 2: File an online industrial/wage dispute application on the SAMADHAN portal (samadhan.labour.gov.in) or before the District Labour Officer.", "चरण 2: समाधान पोर्टल (samadhan.labour.gov.in) या जिला श्रम अधिकारी के समक्ष ऑनलाइन आवेदन दर्ज करें।"),
                BilingualText("Step 3: Attend conciliation notice meetings called by the Labour Conciliation Officer.", "चरण 3: श्रम सुलह अधिकारी द्वारा बुलाई गई सुलह बैठक में भाग लें।")
            ),
            nearbyAidSuggestion = BilingualText(
                "Workers and low-income employees can obtain free legal representation through District Legal Services Authority (DLSA) Labour Cells.",
                "श्रमिक और कम आय वाले कर्मचारी जिला विधिक सेवा प्राधिकरण (DLSA) के माध्यम से निःशुल्क कानूनी सहायता प्राप्त कर सकते हैं।"
            ),
            emergencyNote = BilingualText(
                "For bonded labour, child labour, or physical confinement at workplace, call 112 or Childline 1098 immediately.",
                "बंधुआ मजदूरी, बाल श्रम या कार्यस्थल पर बंधक बनाए जाने की स्थिति में तुरंत 112 या चाइल्डलाइन 1098 पर कॉल करें।"
            ),
            uncertaintyDisclaimer = BilingualText(
                "Note: Legal remedies differ significantly depending on whether your role is classified as a 'Workman' under the Industrial Disputes Act or a managerial/supervisory contract governed by civil contract law and State Shops & Establishments Acts.",
                "नोट: कानूनी उपाय इस बात पर निर्भर करते हैं कि आपका पद औद्योगिक विवाद अधिनियम के तहत 'कर्मकार' (Workman) की श्रेणी में आता है या प्रबंधकीय/सिविल अनुबंध के अंतर्गत।"
            )
        ),
        RemedyGuideItem(
            categoryId = "cybercrime",
            title = BilingualText("Cybercrime & Online Fraud", "साइबर अपराध और ऑनलाइन धोखाधड़ी"),
            subtitle = BilingualText("UPI/banking fraud, phishing links, identity theft, online harassment", "UPI/बैंकिंग धोखाधड़ी, फ़िशिंग लिंक, पहचान की चोरी, ऑनलाइन उत्पीड़न"),
            whatItInvolves = BilingualText(
                "Unauthorized money transfers, OTP/KYC scams, fake investment apps, social media impersonation, or cyber stalking/extortion.",
                "अनधिकृत पैसे ट्रांसफर होना, OTP/KYC ठगी, फर्जी निवेश ऐप्स, सोशल मीडिया पर फर्जी प्रोफाइल या साइबर ब्लैकमेलिंग।"
            ),
            possibleRemedies = listOf(
                BilingualText("Immediate lien/freeze request on fraudulent recipient bank accounts via 1930 helpline", "1930 हेल्पलाइन के माध्यम से धोखाधड़ी वाले बैंक खातों को तुरंत फ्रीज/होल्ड कराने का अनुरोध"),
                BilingualText("Formal Cybercrime Complaint / FIR registration via National Cyber Crime Reporting Portal", "राष्ट्रीय साइबर अपराध रिपोर्टिंग पोर्टल के माध्यम से औपचारिक शिकायत / एफआईआर दर्ज करना"),
                BilingualText("Zero-liability protection notice to your Bank/Payment System Provider under RBI guidelines (if reported promptly)", "समय पर सूचना देने पर RBI दिशानिर्देशों के तहत अपने बैंक को अनधिकृत लेनदेन की सूचना देना")
            ),
            verifiedLawProvisions = BilingualText(
                "• Information Technology Act, 2000: Section 66C (Identity theft), Section 66D (Cheating by personation using computer resource), Section 66E (Privacy violation).\n• Bharatiya Nyaya Sanhita, 2023 (BNS): Section 318 (Cheating), Section 319 (Cheating by personation).\n• Verified Source: Indian Cyber Crime Coordination Centre (I4C), Ministry of Home Affairs (cybercrime.gov.in).",
                "• सूचना प्रौद्योगिकी अधिनियम, 2000 (IT Act): धारा 66C (पहचान की चोरी), धारा 66D (कंप्यूटर संसाधन द्वारा छद्म रूप से ठगी), धारा 66E (गोपनीयता भंग)।\n• भारतीय न्याय संहिता, 2023 (BNS): धारा 318 (धोखाधड़ी)।\n• स्रोत: गृह मंत्रालय का साइबर क्राइम पोर्टल (cybercrime.gov.in)।"
            ),
            appropriateAuthority = BilingualText(
                "1. National Cyber Crime Helpline: Call 1930 immediately for financial fraud.\n2. National Cyber Crime Reporting Portal: https://cybercrime.gov.in\n3. Local Cyber Crime Police Cell / Station.",
                "1. राष्ट्रीय साइबर अपराध हेल्पलाइन: वित्तीय धोखाधड़ी के लिए तुरंत 1930 पर कॉल करें।\n2. राष्ट्रीय साइबर क्राइम पोर्टल: https://cybercrime.gov.in\n3. स्थानीय साइबर क्राइम पुलिस सेल।"
            ),
            requiredDocuments = listOf(
                BilingualText("12-digit UTR / Transaction Reference Number and Bank Statement showing debit", "12-अंकों का UTR / ट्रांजेक्शन नंबर और बैंक स्टेटमेंट"),
                BilingualText("Screenshots of fraudulent message, URL, caller number, or UPI ID", "धोखाधड़ी वाले संदेश, लिंक, कॉलर नंबर या UPI ID के स्क्रीनशॉट"),
                BilingualText("Do NOT delete SMS or call logs; keep original device evidence intact", "SMS या कॉल लॉग डिलीट न करें; मूल साक्ष्य सुरक्षित रखें")
            ),
            stepByStepGuide = listOf(
                BilingualText("Step 1 (Golden Hour): Call 1930 immediately if money was debited, so the Citizen Financial Cyber Fraud Reporting System can alert banks to hold funds.", "चरण 1 (गोल्डन ऑवर): यदि पैसा कटा है तो तुरंत 1930 पर कॉल करें ताकि बैंकों को फंड होल्ड करने का अलर्ट भेजा जा सके।"),
                BilingualText("Step 2: Inform your bank immediately to block compromised cards/UPI and obtain a complaint acknowledgement number.", "चरण 2: अपने बैंक को तुरंत सूचित करें और कार्ड/UPI ब्लॉक कराकर शिकायत संख्या प्राप्त करें।"),
                BilingualText("Step 3: Submit a detailed complaint with screenshots and bank statement on https://cybercrime.gov.in and download the PDF acknowledgement.", "चरण 3: https://cybercrime.gov.in पर स्क्रीनशॉट और बैंक स्टेटमेंट के साथ विस्तृत शिकायत दर्ज करें।")
            ),
            nearbyAidSuggestion = BilingualText(
                "Visit your District Cyber Police Cell or District Legal Services Authority (DLSA) if court orders are later needed to release frozen funds.",
                "यदि बाद में होल्ड की गई राशि को रिलीज कराने के लिए न्यायालय के आदेश की आवश्यकता हो, तो अपने जिला साइबर सेल या DLSA से संपर्क करें।"
            ),
            emergencyNote = BilingualText(
                "WARNING: Police, CBI, Customs, or Judges NEVER conduct 'Digital Arrest' over WhatsApp/Skype video calls or demand money transfers for verification. Disconnect immediately and call 1930.",
                "चेतावनी: पुलिस, सीबीआई, कस्टम या जज कभी भी व्हाट्सएप/वीडियो कॉल पर 'डिजिटल अरेस्ट' नहीं करते और न ही सत्यापन के लिए पैसे मांगते हैं। तुरंत कॉल काटें और 1930 पर रिपोर्ट करें।"
            ),
            uncertaintyDisclaimer = BilingualText(
                "Note: Recovery of defrauded funds depends on how quickly the transaction is reported before funds are withdrawn or layered by fraudsters, and requires subsequent police investigation and magistrate/court release orders.",
                "नोट: ठगी गई राशि की वापसी इस बात पर निर्भर करती है कि पैसा निकाले जाने से पहले कितनी जल्दी रिपोर्ट किया गया है, तथा इसके लिए पुलिस जांच और न्यायालय के आदेश की आवश्यकता होती है।"
            )
        ),
        RemedyGuideItem(
            categoryId = "rental_property",
            title = BilingualText("Rental & Property Problems", "किराया और संपत्ति संबंधी समस्याएं"),
            subtitle = BilingualText("Security deposit refund, unlawful eviction threat, rent agreement disputes", "सिक्योरिटी डिपॉजिट वापसी, अवैध बेदखली की धमकी, किराया समझौता विवाद"),
            whatItInvolves = BilingualText(
                "Disputes between tenants and landlords over non-return of security deposit after vacating, sudden disconnection of essential utilities (water/electricity), or lockouts without due process.",
                "मकान खाली करने के बाद सिक्योरिटी डिपॉजिट वापस न करने, बिजली-पानी जैसी आवश्यक सेवाएं काटने, या बिना कानूनी प्रक्रिया के बेदखल करने से जुड़े विवाद।"
            ),
            possibleRemedies = listOf(
                BilingualText("Formal Legal Demand Notice for refund of security deposit after deducting legitimate agreed dues", "वैध बकाया काटने के बाद सिक्योरिटी डिपॉजिट की वापसी के लिए औपचारिक कानूनी नोटिस"),
                BilingualText("Application before Rent Authority / Rent Controller (under applicable State Rent Control / Tenancy Act)", "किराया प्राधिकरण / रेंट कंट्रोलर के समक्ष आवेदन (राज्य किराया नियंत्रण अधिनियम के अनुसार)"),
                BilingualText("Pre-Litigation Mediation via District Legal Services Authority (DLSA) or Civil Summary Suit for money recovery", "DLSA के माध्यम से प्री-लिटिगेशन मध्यस्थता या धन वसूली के लिए सिविल वाद")
            ),
            verifiedLawProvisions = BilingualText(
                "• Transfer of Property Act, 1882 (Sections 105–117 governing leases).\n• Applicable State Rent Control Acts / Model Tenancy Act provisions as adopted by the respective State.\n• Indian Contract Act, 1872 (breach of lease agreement terms).",
                "• संपत्ति अंतरण अधिनियम, 1882 (धारा 105–117 पट्टे/किराये से संबंधित)।\n• संबंधित राज्य का किराया नियंत्रण अधिनियम (State Rent Control Act)।\n• भारतीय अनुबंध अधिनियम, 1872 (समझौते की शर्तों का उल्लंघन)।"
            ),
            appropriateAuthority = BilingualText(
                "• District Mediation Centre / Pre-Litigation Lok Adalat (for fast mutual settlement of deposit disputes).\n• Rent Controller / Rent Authority or Civil Court having territorial jurisdiction where the property is situated.",
                "• जिला मध्यस्थता केंद्र / प्री-लिटिगेशन लोक अदालत (डिपॉजिट विवाद के आपसी समाधान हेतु)।\n• क्षेत्राधिकार रखने वाला रेंट कंट्रोलर या सिविल न्यायालय।"
            ),
            requiredDocuments = listOf(
                BilingualText("Executed Rent / Lease Agreement and inventory list", "किराया समझौता (Rent Agreement) और सामान की सूची"),
                BilingualText("Bank transfer proof of security deposit and monthly rent payments", "सिक्योरिटी डिपॉजिट और मासिक किराए के बैंक भुगतान का प्रमाण"),
                BilingualText("Notice of vacation, electricity/water bill clearance receipts, and handover photos", "मकान खाली करने का नोटिस, बिजली/पानी के बिल भुगतान की रसीदें और चाबी सौंपने के प्रमाण")
            ),
            stepByStepGuide = listOf(
                BilingualText("Step 1: Take dated photos/video of the clean vacated premises and utility meter readings at handover.", "चरण 1: मकान खाली करते समय साफ परिसर और बिजली/पानी मीटर रीडिंग के तारीख सहित फोटो/वीडियो लें।"),
                BilingualText("Step 2: Send a written settlement statement and formal notice requesting deposit return within 15 days.", "चरण 2: लिखित हिसाब और औपचारिक नोटिस भेजकर 15 दिनों के भीतर डिपॉजिट लौटाने का अनुरोध करें।"),
                BilingualText("Step 3: Apply for Pre-Litigation Mediation at your District Legal Services Authority (DLSA) before initiating costly civil court litigation.", "चरण 3: महंगे कोर्ट केस से पहले अपने जिला विधिक सेवा प्राधिकरण (DLSA) में प्री-लिटिगेशन मध्यस्थता के लिए आवेदन करें।")
            ),
            nearbyAidSuggestion = BilingualText(
                "District Court Mediation Centres and DLSA Permanent/Pre-Litigation clinics handle tenancy deposit settlements efficiently.",
                "जिला न्यायालय मध्यस्थता केंद्र और DLSA प्री-लिटिगेशन क्लिनिक किराया डिपॉजिट विवादों को सुलझाने में मदद करते हैं।"
            ),
            emergencyNote = BilingualText(
                "If facing unlawful physical force, lock-breaking, or threat of violence, dial 112 immediately.",
                "यदि अवैध शारीरिक बल, ताला तोड़ने या हिंसा की धमकी का सामना करना पड़ रहा हो, तो तुरंत 112 डायल करें।"
            ),
            uncertaintyDisclaimer = BilingualText(
                "Note: Tenancy laws are State subjects in India. Specific rights, notice periods, and Rent Authority powers vary by State and by the written terms of your Rent Agreement.",
                "नोट: भारत में किरायेदारी कानून राज्य का विषय हैं। विशिष्ट अधिकार और नोटिस की अवधि आपके राज्य के कानून और आपके किराया समझौते की शर्तों पर निर्भर करती है।"
            )
        ),
        RemedyGuideItem(
            categoryId = "family",
            title = BilingualText("Family & Matrimonial Disputes", "पारिवारिक और वैवाहिक विवाद"),
            subtitle = BilingualText("Maintenance, domestic violence protection, family property, mediation", "भरण-पोषण (Maintenance), घरेलू हिंसा से सुरक्षा, पारिवारिक मध्यस्थता"),
            whatItInvolves = BilingualText(
                "Matters concerning domestic safety, maintenance for spouse/children/elderly parents, child custody, or family property partition.",
                "घरेलू सुरक्षा, पत्नी/बच्चों/बुजुर्ग माता-पिता के भरण-पोषण, बच्चों की कस्टडी या पारिवारिक संपत्ति से जुड़े मामले।"
            ),
            possibleRemedies = listOf(
                BilingualText("Protection Order, Residence Order, and Monetary Relief under Domestic Violence Act", "घरेलू हिंसा अधिनियम के तहत सुरक्षा आदेश, निवास आदेश और आर्थिक सहायता"),
                BilingualText("Maintenance order for spouse, children, or senior citizen parents", "पत्नी, बच्चों या वरिष्ठ नागरिक माता-पिता के लिए भरण-पोषण आदेश"),
                BilingualText("Confidential Family Counselling and Mediation through Family Court or DLSA", "पारिवारिक न्यायालय या DLSA के माध्यम से गोपनीय परामर्श और मध्यस्थता")
            ),
            verifiedLawProvisions = BilingualText(
                "• Protection of Women from Domestic Violence Act, 2005 (Sections 12, 18, 19, 20).\n• Bharatiya Nagarik Suraksha Sanhita, 2023 (BNSS): Section 144 (Order for maintenance of wives, children and parents).\n• Maintenance and Welfare of Parents and Senior Citizens Act, 2007.",
                "• घरेलू हिंसा से महिला संरक्षण अधिनियम, 2005 (धारा 12, 18, 19, 20)।\n• भारतीय नागरिक सुरक्षा संहिता, 2023 (BNSS): धारा 144 (पत्नी, बच्चों और माता-पिता के भरण-पोषण का आदेश)।\n• माता-पिता और वरिष्ठ नागरिकों का भरण-पोषण तथा कल्याण अधिनियम, 2007।"
            ),
            appropriateAuthority = BilingualText(
                "• Protection Officer (District Women & Child Development Dept) or One Stop Centre (Sakhi).\n• Family Court / Judicial Magistrate First Class.\n• Senior Citizen Maintenance Tribunal (Sub-Divisional Magistrate / SDM).",
                "• संरक्षण अधिकारी (महिला एवं बाल विकास विभाग) या वन स्टॉप सेंटर (सखी)।\n• पारिवारिक न्यायालय (Family Court) / न्यायिक मजिस्ट्रेट।\n• वरिष्ठ नागरिक भरण-पोषण अधिकरण (SDM कार्यालय)।"
            ),
            requiredDocuments = listOf(
                BilingualText("Identity & address proof, marriage proof or birth certificates of children", "पहचान और पता प्रमाण, विवाह प्रमाण या बच्चों का जन्म प्रमाण पत्र"),
                BilingualText("Medical reports or photographs (if physical injury occurred)", "मेडिकल रिपोर्ट या फोटो (यदि शारीरिक चोट लगी हो)"),
                BilingualText("Details of income/assets for maintenance applications", "भरण-पोषण आवेदन के लिए आय/संपत्ति का विवरण")
            ),
            stepByStepGuide = listOf(
                BilingualText("Step 1: If in immediate danger, move to a safe location and call 112 or Women Helpline 181.", "चरण 1: तत्काल खतरे की स्थिति में सुरक्षित स्थान पर जाएं और 112 या महिला हेल्पलाइन 181 पर कॉल करें।"),
                BilingualText("Step 2: Contact the District Protection Officer or DLSA Front Office (Women enjoy free legal aid regardless of income under Section 12(c) of Legal Services Authorities Act, 1987).", "चरण 2: जिला संरक्षण अधिकारी या DLSA से संपर्क करें (विधिक सेवा प्राधिकरण अधिनियम, 1987 की धारा 12(c) के तहत सभी महिलाएं आय की परवाह किए बिना निःशुल्क कानूनी सहायता की पात्र हैं)।"),
                BilingualText("Step 3: Consider court-annexed Family Mediation if both parties wish to explore amicable settlement.", "चरण 3: यदि दोनों पक्ष आपसी सहमति से समाधान चाहते हैं तो पारिवारिक मध्यस्थता केंद्र की सहायता लें।")
            ),
            nearbyAidSuggestion = BilingualText(
                "All women and children are statutorily entitled to 100% Free Legal Aid at every District Legal Services Authority (DLSA).",
                "सभी महिलाएं और बच्चे प्रत्येक जिला विधिक सेवा प्राधिकरण (DLSA) में 100% निःशुल्क कानूनी सहायता के वैधानिक रूप से हकदार हैं।"
            ),
            emergencyNote = BilingualText(
                "Emergency Police: 112 | Women Helpline: 181 | NCW Helpline: 7827170170 | Elderline: 14567",
                "आपातकालीन पुलिस: 112 | महिला हेल्पलाइन: 181 | राष्ट्रीय महिला आयोग: 7827170170 | एल्डरलाइन: 14567"
            ),
            uncertaintyDisclaimer = BilingualText(
                "Note: Family laws in India include both secular statutes and personal laws depending on how the marriage was solemnized. Consult a DLSA panel advocate or family lawyer for case-specific advice.",
                "नोट: भारत में पारिवारिक मामलों में धर्मनिरपेक्ष कानून और व्यक्तिगत कानून दोनों लागू हो सकते हैं। अपने मामले की विशिष्ट सलाह के लिए DLSA पैनल वकील से परामर्श लें।"
            )
        ),
        RemedyGuideItem(
            categoryId = "banking_payment",
            title = BilingualText("Banking & Payment Problems", "बैंकिंग और भुगतान समस्याएं"),
            subtitle = BilingualText("Failed UPI/NEFT debits, wrongful bank charges, loan harassment, ATM failures", "फेल UPI/NEFT कटौती, गलत बैंक शुल्क, लोन वसूली उत्पीड़न, एटीएम विफलता"),
            whatItInvolves = BilingualText(
                "Issues where money is debited from your bank account during a failed UPI/ATM transaction and not auto-reversed within RBI's Turn Around Time (TAT), or unresolved banking service complaints.",
                "ऐसे मामले जहां फेल UPI या ATM लेनदेन में खाते से पैसा कट गया हो और निर्धारित समय में वापस न आया हो, या बैंक सेवा से जुड़ी अनसुलझी शिकायतें।"
            ),
            possibleRemedies = listOf(
                BilingualText("Auto-reversal with statutory compensation per day of delay beyond RBI Turn Around Time (TAT)", "RBI द्वारा निर्धारित समय-सीमा (TAT) के बाद देरी होने पर प्रतिदिन के मुआवजे के साथ राशि की वापसी"),
                BilingualText("Escalation to Reserve Bank Integrated Ombudsman (RB-IOS) after 30 days of bank complaint", "बैंक में शिकायत के 30 दिन बाद भारतीय रिजर्व बैंक लोकपाल (RB-IOS) में अपील")
            ),
            verifiedLawProvisions = BilingualText(
                "• Reserve Bank - Integrated Ombudsman Scheme, 2021 (RB-IOS).\n• RBI Circular on Harmonisation of Turn Around Time (TAT) and customer compensation for failed transactions (2019).\n• Verified Portal: https://cms.rbi.org.in",
                "• रिजर्व बैंक - एकीकृत लोकपाल योजना, 2021 (RB-IOS)।\n• विफल लेनदेन के लिए टर्न अराउंड टाइम (TAT) और ग्राहक मुआवजे पर RBI परिपत्र।\n• आधिकारिक पोर्टल: https://cms.rbi.org.in"
            ),
            appropriateAuthority = BilingualText(
                "1. Your Bank's Nodal Grievance Officer (Mandatory first step).\n2. RBI Complaint Management System (CMS): https://cms.rbi.org.in or Call 14448.",
                "1. आपके बैंक का नोडल शिकायत अधिकारी (अनिवार्य पहला कदम)।\n2. RBI शिकायत प्रबंधन प्रणाली (CMS): https://cms.rbi.org.in या टोल-फ्री 14448।"
            ),
            requiredDocuments = listOf(
                BilingualText("Bank account statement showing debit entry and Transaction Reference Number", "डेबिट प्रविष्टि और ट्रांजेक्शन रेफरेंस नंबर दिखाने वाला बैंक स्टेटमेंट"),
                BilingualText("Copy of written complaint submitted to the regulated bank and their reply (or proof that 30 days elapsed)", "बैंक को दी गई लिखित शिकायत की प्रति और उनका उत्तर (या 30 दिन बीतने का प्रमाण)")
            ),
            stepByStepGuide = listOf(
                BilingualText("Step 1: Lodge a written complaint with your bank/payment app and note the Service Request (SR) number.", "चरण 1: अपने बैंक या पेमेंट ऐप में लिखित शिकायत दर्ज करें और सर्विस रिक्वेस्ट (SR) नंबर नोट करें।"),
                BilingualText("Step 2: Wait for the bank's resolution (up to 30 days as required under RBI Ombudsman rules, or T+1/T+5 days for failed auto-reversals).", "चरण 2: बैंक के समाधान की प्रतीक्षा करें (लोकपाल नियमों के तहत अधिकतम 30 दिन)।"),
                BilingualText("Step 3: If the bank rejects your complaint or fails to reply within 30 days, file a free complaint on https://cms.rbi.org.in.", "चरण 3: यदि बैंक शिकायत खारिज करता है या 30 दिनों में जवाब नहीं देता, तो https://cms.rbi.org.in पर निःशुल्क शिकायत दर्ज करें।")
            ),
            nearbyAidSuggestion = BilingualText(
                "RBI Contact Centre 14448 provides guidance in Hindi, English, and regional languages on filing Ombudsman complaints.",
                "RBI संपर्क केंद्र 14448 हिंदी और अंग्रेजी में लोकपाल शिकायत दर्ज करने पर मार्गदर्शन प्रदान करता है।"
            ),
            emergencyNote = BilingualText(
                "If the transaction was unauthorized fraud/scam (not a technical failure), do NOT wait 30 days — call 1930 immediately.",
                "यदि लेनदेन धोखाधड़ी/ठगी था (तकनीकी विफलता नहीं), तो 30 दिन प्रतीक्षा न करें — तुरंत 1930 पर कॉल करें।"
            ),
            uncertaintyDisclaimer = BilingualText(
                "Note: The RBI Ombudsman requires that you first complain in writing to the concerned bank and wait 30 days (unless the bank replies earlier with a rejection) before approaching RB-IOS.",
                "नोट: RBI लोकपाल में जाने से पहले संबंधित बैंक में लिखित शिकायत करना और 30 दिन प्रतीक्षा करना (जब तक बैंक पहले ही अस्वीकार न कर दे) अनिवार्य है।"
            )
        )
    )

    val rightsFaqs = listOf(
        RightsFaqItem(
            id = "shop_refund",
            sampleQuestion = BilingualText(
                "The shop is refusing to refund my money for a defective product.",
                "दुकानदार खराब सामान के पैसे वापस (रिफंड) करने से मना कर रहा है।"
            ),
            keywords = listOf("shop", "refund", "defective", "return", "bill", "receipt", "दुकान", "रिफंड", "पैसे", "खराब"),
            simpleExplanation = BilingualText(
                "Under Indian law, writing 'No Exchange / No Refund' on a bill does not take away your statutory right if the product sold to you is defective, counterfeit, expired, or does not match its description.",
                "भारतीय कानून के तहत, यदि बेचा गया सामान खराब, नकली, एक्सपायर्ड या बताए गए विवरण के अनुसार नहीं है, तो बिल पर 'नो रिफंड / नो एक्सचेंज' लिखने से आपका कानूनी अधिकार समाप्त नहीं होता।"
            ),
            applicableRights = listOf(
                BilingualText("Right to be protected against defective goods and unfair trade practices", "खराब सामान और अनुचित व्यापार व्यवहार के विरुद्ध सुरक्षा का अधिकार"),
                BilingualText("Right to seek repair, replacement, or refund of price paid", "मरम्मत, बदलाव या भुगतान की गई कीमत के रिफंड की मांग करने का अधिकार")
            ),
            verifiedLegalSources = BilingualText(
                "Consumer Protection Act, 2019 — Section 2(9) (Consumer Rights), Section 2(10) (Defect), Section 2(47) (Unfair Trade Practice). Source: India Code.",
                "उपभोक्ता संरक्षण अधिनियम, 2019 — धारा 2(9) (उपभोक्ता अधिकार), धारा 2(10) (दोष), धारा 2(47) (अनुचित व्यापार व्यवहार)। स्रोत: इंडिया कोड।"
            ),
            practicalSteps = listOf(
                BilingualText("Show the defect clearly and send a written message/notice to the seller requesting replacement or refund.", "दुकानदार को खराबी स्पष्ट दिखाएं और लिखित संदेश/नोटिस भेजकर बदलने या रिफंड की मांग करें।"),
                BilingualText("Use Nyaya Sahayak's 'Refund Recovery Assistance' to generate a formal Refund Demand Notice.", "औपचारिक रिफंड नोटिस तैयार करने के लिए न्याय सहायक के 'रिफंड रिकवरी' टूल का उपयोग करें।"),
                BilingualText("Register a grievance on the National Consumer Helpline (1915 / consumerhelpline.gov.in).", "राष्ट्रीय उपभोक्ता हेल्पलाइन (1915 / consumerhelpline.gov.in) पर शिकायत दर्ज करें।")
            ),
            documentsToPreserve = listOf(
                BilingualText("Original bill/invoice or UPI/card payment screenshot", "मूल बिल/रसीद या UPI/कार्ड भुगतान का स्क्रीनशॉट"),
                BilingualText("Clear photos/videos of the defective item and packaging", "खराब सामान और पैकेजिंग के स्पष्ट फोटो/वीडियो")
            ),
            whereToComplain = BilingualText(
                "National Consumer Helpline (1915) or District Consumer Disputes Redressal Commission via e-Jagriti (e-jagriti.gov.in).",
                "राष्ट्रीय उपभोक्ता हेल्पलाइन (1915) या ई-जागृति (e-jagriti.gov.in) के माध्यम से जिला उपभोक्ता आयोग।"
            ),
            legalAidContact = BilingualText(
                "Call NCH 1915 or NALSA Legal Aid Helpline 15100.",
                "उपभोक्ता हेल्पलाइन 1915 या NALSA हेल्पलाइन 15100 पर कॉल करें।"
            )
        ),
        RightsFaqItem(
            id = "unpaid_salary",
            sampleQuestion = BilingualText(
                "My employer has not paid my salary.",
                "मेरे नियोक्ता (कंपनी/मालिक) ने मेरा वेतन नहीं दिया है।"
            ),
            keywords = listOf("employer", "salary", "wages", "unpaid", "job", "company", "वेतन", "सैलरी", "मालिक", "कंपनी"),
            simpleExplanation = BilingualText(
                "Every employee or worker who has performed work is legally entitled to timely payment of earned wages/salary. Withholding earned salary without lawful grounds violates labour and contract laws.",
                "प्रत्येक कर्मचारी या श्रमिक जिसने काम किया है, वह समय पर अपने अर्जित वेतन के भुगतान का कानूनी हकदार है। बिना वैध कारण के वेतन रोकना श्रम और अनुबंध कानूनों का उल्लंघन है।"
            ),
            applicableRights = listOf(
                BilingualText("Right to timely payment of earned wages/salary", "अर्जित वेतन के समय पर भुगतान का अधिकार"),
                BilingualText("Right to approach Labour Conciliation Officer or file recovery application", "श्रम सुलह अधिकारी से संपर्क करने या वसूली आवेदन करने का अधिकार")
            ),
            verifiedLegalSources = BilingualText(
                "Payment of Wages Act, 1936; Industrial Disputes Act, 1947 (Section 33C for recovery of money due from employer); Indian Contract Act, 1872.",
                "वेतन भुगतान अधिनियम, 1936; औद्योगिक विवाद अधिनियम, 1947 (नियोक्ता से बकाया राशि की वसूली के लिए धारा 33C); भारतीय अनुबंध अधिनियम, 1872।"
            ),
            practicalSteps = listOf(
                BilingualText("Send a polite but firm written email/letter to HR & Management detailing the unpaid months and exact amount.", "एचआर और प्रबंधन को बकाया महीनों और राशि का विवरण देते हुए लिखित ईमेल/पत्र भेजें।"),
                BilingualText("Lodge an online grievance on the Ministry of Labour's SAMADHAN portal (samadhan.labour.gov.in) or State Labour Department.", "श्रम मंत्रालय के समाधान पोर्टल (samadhan.labour.gov.in) या राज्य श्रम विभाग में ऑनलाइन शिकायत दर्ज करें।"),
                BilingualText("Visit your District Legal Services Authority (DLSA) for free pre-litigation notice assistance.", "निःशुल्क प्री-लिटिगेशन नोटिस सहायता के लिए अपने जिला विधिक सेवा प्राधिकरण (DLSA) में जाएं।")
            ),
            documentsToPreserve = listOf(
                BilingualText("Offer letter, appointment contract, and employee ID", "ऑफर लेटर, नियुक्ति पत्र और कर्मचारी आईडी"),
                BilingualText("Previous salary slips and bank statement showing non-credit of recent months", "पुरानी सैलरी स्लिप और बैंक स्टेटमेंट"),
                BilingualText("Work emails, attendance logs, or WhatsApp messages proving you worked during that period", "उस अवधि में काम करने का प्रमाण देने वाले ईमेल, उपस्थिति रिकॉर्ड या संदेश")
            ),
            whereToComplain = BilingualText(
                "SAMADHAN Portal (samadhan.labour.gov.in), District Labour Commissioner Office, or DLSA Pre-Litigation Cell.",
                "समाधान पोर्टल (samadhan.labour.gov.in), जिला श्रम आयुक्त कार्यालय, या DLSA प्री-लिटिगेशन सेल।"
            ),
            legalAidContact = BilingualText(
                "NALSA Free Legal Aid Helpline: 15100.",
                "NALSA निःशुल्क विधिक सहायता हेल्पलाइन: 15100।"
            )
        ),
        RightsFaqItem(
            id = "police_refusal",
            sampleQuestion = BilingualText(
                "The police are not accepting my complaint.",
                "पुलिस मेरी शिकायत (FIR/शिकायत) दर्ज नहीं कर रही है।"
            ),
            keywords = listOf("police", "fir", "complaint", "station", "refusing", "थाना", "पुलिस", "एफआईआर", "शिकायत"),
            simpleExplanation = BilingualText(
                "If your complaint discloses a 'cognizable offence' (serious crimes like theft, assault, cyber fraud, domestic cruelty), the police officer in charge is legally bound to register an FIR. For 'non-cognizable' offences (minor disputes), the police record it in the daily diary/NCR register and refer you to the Magistrate.",
                "यदि आपकी शिकायत किसी 'संज्ञेय अपराध' (Cognizable Offence - जैसे चोरी, गंभीर मारपीट, धोखाधड़ी) से संबंधित है, तो थाना प्रभारी एफआईआर दर्ज करने के लिए कानूनी रूप से बाध्य है। 'असंज्ञेय' (Non-cognizable) मामलों में पुलिस एनसीआर (NCR) दर्ज कर मजिस्ट्रेट के पास जाने की सलाह देती है।"
            ),
            applicableRights = listOf(
                BilingualText("Right to mandatory registration of FIR for cognizable offences and free copy of FIR to informant", "संज्ञेय अपराधों में अनिवार्य FIR पंजीकरण और शिकायतकर्ता को FIR की निःशुल्क प्रति मिलने का अधिकार"),
                BilingualText("Zero FIR right: Information can be given at ANY police station irrespective of territorial area", "जीरो एफआईआर (Zero FIR) का अधिकार: क्षेत्राधिकार की परवाह किए बिना किसी भी पुलिस स्टेशन में सूचना दी जा सकती है"),
                BilingualText("Right to escalate in writing by post to the Superintendent of Police (SP/DCP) if SHO refuses", "थाना प्रभारी (SHO) द्वारा मना करने पर पुलिस अधीक्षक (SP/DCP) को डाक द्वारा लिखित शिकायत भेजने का अधिकार")
            ),
            verifiedLegalSources = BilingualText(
                "Bharatiya Nagarik Suraksha Sanhita, 2023 (BNSS): Section 173(1) (Information in cognizable cases, including Zero FIR and electronic communication), Section 173(2) (Free copy of FIR), Section 173(4) (Escalation to Superintendent of Police and application before Magistrate), Section 174 (Non-cognizable cases). Also Supreme Court Constitution Bench ruling in Lalita Kumari v. Govt. of U.P. (2014).",
                "भारतीय नागरिक सुरक्षा संहिता, 2023 (BNSS): धारा 173(1) (संज्ञेय मामलों में सूचना एवं जीरो FIR), धारा 173(2) (FIR की निःशुल्क प्रति), धारा 173(4) (पुलिस अधीक्षक को शिकायत एवं मजिस्ट्रेट के समक्ष आवेदन), धारा 174 (असंज्ञेय मामले)। साथ ही सर्वोच्च न्यायालय का ललिता कुमारी बनाम उत्तर प्रदेश सरकार (2014) निर्णय।"
            ),
            practicalSteps = listOf(
                BilingualText("Use Nyaya Sahayak's 'Police Refusal Assistance' module to check whether your matter is cognizable or civil/non-cognizable.", "यह समझने के लिए कि आपका मामला संज्ञेय है या सिविल/असंज्ञेय, 'पुलिस अस्वीकृति सहायता' मॉड्यूल का उपयोग करें।"),
                BilingualText("Send the signed written complaint via Registered Post / Speed Post (with Acknowledgement Due) to the Superintendent of Police (SP) / Deputy Commissioner of Police (DCP) under Section 173(4) BNSS.", "धारा 173(4) BNSS के तहत पुलिस अधीक्षक (SP/DCP) को स्पीड पोस्ट/रजिस्टर्ड पोस्ट से लिखित शिकायत भेजें और डाक रसीद संभाल कर रखें।"),
                BilingualText("If still no action is taken, file an application before the Judicial Magistrate through a lawyer or DLSA Legal Aid Counsel.", "यदि फिर भी कार्रवाई न हो, तो वकील या DLSA विधिक सहायता अधिवक्ता के माध्यम से न्यायिक मजिस्ट्रेट के समक्ष आवेदन करें।")
            ),
            documentsToPreserve = listOf(
                BilingualText("Signed copy of your original written complaint with date and time", "आपकी मूल लिखित शिकायत की हस्ताक्षरित प्रति (तारीख और समय सहित)"),
                BilingualText("Speed Post / Registered Post receipt and tracking delivery status to SP/DCP office", "SP/DCP कार्यालय को भेजे गए स्पीड पोस्ट की रसीद और डिलीवरी ट्रैकिंग प्रमाण")
            ),
            whereToComplain = BilingualText(
                "Office of Superintendent of Police (SP) / Commissioner of Police, State Police Citizen Portal (CCTNS), or Judicial Magistrate Court.",
                "पुलिस अधीक्षक (SP) / पुलिस कमिश्नर कार्यालय, राज्य पुलिस सिटीजन पोर्टल (CCTNS), या न्यायिक मजिस्ट्रेट न्यायालय।"
            ),
            legalAidContact = BilingualText(
                "District Legal Services Authority (DLSA) at your District Court or Helpline 15100.",
                "आपके जिला न्यायालय में जिला विधिक सेवा प्राधिकरण (DLSA) या हेल्पलाइन 15100।"
            )
        ),
        RightsFaqItem(
            id = "online_service_not_received",
            sampleQuestion = BilingualText(
                "I paid online but did not receive the product or service.",
                "मैंने ऑनलाइन भुगतान कर दिया लेकिन मुझे सामान या सेवा नहीं मिली।"
            ),
            keywords = listOf("online", "paid", "service", "delivery", "ecommerce", "website", "ऑनलाइन", "भुगतान", "डिलीवरी"),
            simpleExplanation = BilingualText(
                "E-commerce platforms and online sellers are legally required under the Consumer Protection (E-Commerce) Rules, 2020 to appoint a Grievance Officer, acknowledge complaints within 48 hours, and resolve them within 1 month.",
                "उपभोक्ता संरक्षण (ई-कॉमर्स) नियम, 2020 के तहत ऑनलाइन कंपनियों और ई-कॉमर्स प्लेटफॉर्म के लिए शिकायत अधिकारी नियुक्त करना, 48 घंटे में शिकायत की पावती देना और 1 महीने में समाधान करना अनिवार्य है।"
            ),
            applicableRights = listOf(
                BilingualText("Right to full refund for non-delivery of paid goods or services", "भुगतान किए गए सामान या सेवा की डिलीवरी न होने पर पूरे रिफंड का अधिकार"),
                BilingualText("Right to Grievance Officer escalation within 48 hours under E-Commerce Rules, 2020", "ई-कॉमर्स नियम, 2020 के तहत 48 घंटे के भीतर शिकायत पावती पाने का अधिकार")
            ),
            verifiedLegalSources = BilingualText(
                "Consumer Protection Act, 2019 & Consumer Protection (E-Commerce) Rules, 2020 (Rule 4 & Rule 5).",
                "उपभोक्ता संरक्षण अधिनियम, 2019 एवं उपभोक्ता संरक्षण (ई-कॉमर्स) नियम, 2020 (नियम 4 और 5)।"
            ),
            practicalSteps = listOf(
                BilingualText("Email the platform's Grievance Officer (listed in the website's 'Contact Us' / 'Terms' footer).", "वेबसाइट के 'Contact Us' में दिए गए शिकायत अधिकारी (Grievance Officer) को ईमेल करें।"),
                BilingualText("If you paid via Credit Card or UPI to a merchant and suspect scam, raise a Chargeback / Dispute with your bank immediately.", "यदि क्रेडिट कार्ड या UPI से भुगतान किया है, तो अपने बैंक में चार्जबैक/डिस्प्यूट अनुरोध भी दर्ज करें।"),
                BilingualText("File a docket on National Consumer Helpline (1915) or Cybercrime Portal (1930) if the website itself is a fake scam site.", "राष्ट्रीय उपभोक्ता हेल्पलाइन (1915) पर शिकायत करें, और यदि वेबसाइट फर्जी ठगी वाली है तो 1930 पर रिपोर्ट करें।")
            ),
            documentsToPreserve = listOf(
                BilingualText("Order confirmation screenshot, invoice, and payment UTR number", "ऑर्डर कन्फर्मेशन स्क्रीनशॉट, इनवॉइस और भुगतान UTR नंबर"),
                BilingualText("URL of the website/app and emails exchanged with support", "वेबसाइट/ऐप का लिंक और कस्टमर सपोर्ट के साथ हुए ईमेल")
            ),
            whereToComplain = BilingualText(
                "National Consumer Helpline (1915 / consumerhelpline.gov.in) for legitimate companies; Cybercrime (1930) for fraudulent phishing websites.",
                "वैध कंपनियों के लिए राष्ट्रीय उपभोक्ता हेल्पलाइन (1915); फर्जी वेबसाइटों के लिए साइबर हेल्पलाइन (1930)।"
            ),
            legalAidContact = BilingualText(
                "National Consumer Helpline: 1915 | Cyber Helpline: 1930",
                "राष्ट्रीय उपभोक्ता हेल्पलाइन: 1915 | साइबर हेल्पलाइन: 1930"
            )
        ),
        RightsFaqItem(
            id = "landlord_deposit",
            sampleQuestion = BilingualText(
                "My landlord is refusing to return my security deposit.",
                "मेरा मकान मालिक मेरी सिक्योरिटी डिपॉजिट की रकम वापस नहीं कर रहा है।"
            ),
            keywords = listOf("landlord", "deposit", "rent", "tenant", "vacate", "मकान", "किराया", "डिपॉजिट"),
            simpleExplanation = BilingualText(
                "A security deposit is held in trust to cover unpaid rent or actual damage beyond normal wear and tear. Once you vacate and clear utility bills, the landlord cannot arbitrarily forfeit your deposit.",
                "सिक्योरिटी डिपॉजिट केवल बकाया किराया या सामान्य उपयोग से अधिक वास्तविक नुकसान की भरपाई के लिए रखा जाता है। मकान खाली करने और बिल चुकाने के बाद मकान मालिक मनमाने ढंग से डिपॉजिट नहीं रोक सकता।"
            ),
            applicableRights = listOf(
                BilingualText("Right to refund of refundable security deposit upon handover of vacant possession", "मकान खाली कर कब्जा सौंपने पर रिफंडेबल सिक्योरिटी डिपॉजिट वापस पाने का अधिकार"),
                BilingualText("Right to itemized account of any deductions claimed by landlord", "मकान मालिक द्वारा की गई किसी भी कटौती का लिखित हिसाब मांगने का अधिकार")
            ),
            verifiedLegalSources = BilingualText(
                "Indian Contract Act, 1872 (Section 73 - Breach of contract); Transfer of Property Act, 1882 (Section 108); Applicable State Tenancy / Rent Control Laws.",
                "भारतीय अनुबंध अधिनियम, 1872 (धारा 73); संपत्ति अंतरण अधिनियम, 1882 (धारा 108); संबंधित राज्य किराया कानून।"
            ),
            practicalSteps = listOf(
                BilingualText("Send a formal written demand notice (via Registered Post and WhatsApp/Email) stating the handover date, cleared electricity/water bills, and bank details for refund.", "मकान सौंपने की तारीख, बिजली/पानी के क्लियर बिल और बैंक विवरण लिखकर रजिस्टर्ड डाक और ईमेल से औपचारिक मांग नोटिस भेजें।"),
                BilingualText("Approach the District Legal Services Authority (DLSA) for Pre-Litigation Mediation / Lok Adalat — most landlords settle when summoned for mediation.", "जिला विधिक सेवा प्राधिकरण (DLSA) में प्री-लिटिगेशन मध्यस्थता के लिए आवेदन करें — मध्यस्थता नोटिस मिलने पर अधिकांश मामलों का निपटारा हो जाता है।")
            ),
            documentsToPreserve = listOf(
                BilingualText("Rent Agreement and bank transfer proof of initial deposit", "किरायानामा (Rent Agreement) और डिपॉजिट भुगतान का बैंक प्रमाण"),
                BilingualText("Photos/videos of the apartment at the time of vacating and final utility bills", "खाली करते समय मकान के फोटो/वीडियो और अंतिम बिजली-पानी के बिल")
            ),
            whereToComplain = BilingualText(
                "DLSA Pre-Litigation Mediation Centre, State Rent Authority, or Civil Court (Summary Recovery).",
                "DLSA प्री-लिटिगेशन मध्यस्थता केंद्र, राज्य किराया प्राधिकरण, या सिविल न्यायालय।"
            ),
            legalAidContact = BilingualText(
                "NALSA / DLSA Helpline: 15100.",
                "NALSA / DLSA हेल्पलाइन: 15100।"
            )
        )
    )

    val authorityComparisonList = listOf(
        AuthorityComparisonItem(
            id = "nch",
            name = BilingualText("National Consumer Helpline (NCH - 1915)", "राष्ट्रीय उपभोक्ता हेल्पलाइन (NCH - 1915)"),
            bestForIssues = listOf("Refund & Service Issues", "Defective Products", "E-Commerce Delays", "Billing Overcharges"),
            simpleExplanation = BilingualText(
                "An official pre-litigation grievance redressal mechanism run by the Department of Consumer Affairs. NCH forwards your complaint directly to partner companies for rapid resolution without going to court.",
                "उपभोक्ता मामले विभाग द्वारा संचालित एक आधिकारिक प्री-लिटिगेशन शिकायत तंत्र। यह बिना कोर्ट गए त्वरित समाधान के लिए आपकी शिकायत सीधे कंपनी को भेजता है।"
            ),
            costAndSpeed = BilingualText("Free | Usually 15–30 days", "निःशुल्क | सामान्यतः 15–30 दिन"),
            isCourtProceeding = false,
            officialPortalOrContact = "Call 1915 | https://consumerhelpline.gov.in",
            whenToChoose = BilingualText(
                "Always try this FIRST for consumer, shopping, telecom, airline, or delivery refund issues before filing a formal court case.",
                "उपभोक्ता, शॉपिंग, टेलीकॉम या रिफंड विवादों में औपचारिक केस दर्ज करने से पहले हमेशा इसे पहले चुनें।"
            )
        ),
        AuthorityComparisonItem(
            id = "consumer_commission",
            name = BilingualText("Consumer Disputes Redressal Commission (e-Jagriti)", "उपभोक्ता विवाद निवारण आयोग (e-Jagriti)"),
            bestForIssues = listOf("Consumer Complaints", "Unresolved Refunds", "Insurance Claim Rejection", "Builder/Housing Delays"),
            simpleExplanation = BilingualText(
                "A quasi-judicial statutory tribunal under the Consumer Protection Act, 2019 that can pass legally binding orders for refund, replacement, and compensation.",
                "उपभोक्ता संरक्षण अधिनियम, 2019 के तहत एक अर्ध-न्यायिक आयोग जो रिफंड, सामान बदलने और मुआवजे के लिए कानूनी रूप से बाध्यकारी आदेश दे सकता है।"
            ),
            costAndSpeed = BilingualText(" ₹0 fee for claims up to ₹5 Lakhs | Statutory target 3–5 months", "₹5 लाख तक के दावों के लिए शून्य शुल्क | 3–5 महीने"),
            isCourtProceeding = true,
            officialPortalOrContact = "https://e-jagriti.gov.in",
            whenToChoose = BilingualText(
                "Choose when the company ignores your written notice and NCH complaint, and you want an enforceable legal order.",
                "जब कंपनी आपके लिखित नोटिस और NCH शिकायत को अनदेखा कर दे और आपको बाध्यकारी कानूनी आदेश चाहिए।"
            )
        ),
        AuthorityComparisonItem(
            id = "dlsa_slsa",
            name = BilingualText("District / State Legal Services Authority (DLSA / SLSA)", "जिला / राज्य विधिक सेवा प्राधिकरण (DLSA / SLSA)"),
            bestForIssues = listOf("Free Legal Aid", "Family Disputes", "Criminal Defense", "Senior Citizen / Women Aid", "Pre-Litigation Notice"),
            simpleExplanation = BilingualText(
                "Statutory authorities in every District Court under the Legal Services Authorities Act, 1987 that provide free lawyers, legal advice, and court fee exemption to eligible citizens.",
                "विधिक सेवा प्राधिकरण अधिनियम, 1987 के तहत प्रत्येक जिला न्यायालय में स्थापित वैधानिक संस्था जो पात्र नागरिकों को निःशुल्क वकील और कानूनी सलाह देती है।"
            ),
            costAndSpeed = BilingualText("100% Free for eligible citizens | Immediate front-office help", "पात्र नागरिकों के लिए 100% निःशुल्क | तत्काल सहायता"),
            isCourtProceeding = false,
            officialPortalOrContact = "Helpline 15100 | https://nalsa.gov.in",
            whenToChoose = BilingualText(
                "Choose when you need a free lawyer, cannot afford legal fees, or belong to eligible categories (Women, Children, SC/ST, PwD, Senior Citizens, or low income).",
                "जब आपको निःशुल्क वकील की आवश्यकता हो या आप पात्र श्रेणी (महिलाएं, बच्चे, SC/ST, दिव्यांगजन, कम आय) में आते हों।"
            )
        ),
        AuthorityComparisonItem(
            id = "lok_adalat",
            name = BilingualText("Lok Adalat & Permanent Lok Adalat", "लोक अदालत और स्थायी लोक अदालत"),
            bestForIssues = listOf("Traffic Challans", "Bank Loan Settlements", "Electricity/Water/Public Utility Disputes", "Matrimonial/Civil Compromise"),
            simpleExplanation = BilingualText(
                "People's Court under the Legal Services Authorities Act where disputes are settled by mutual compromise. A Lok Adalat award has the same force as a Civil Court decree and has NO court fee.",
                "जनता की अदालत जहां आपसी समझौते से विवाद सुलझाए जाते हैं। लोक अदालत का फैसला सिविल कोर्ट की डिक्री के समान बाध्यकारी होता है और इसमें कोई कोर्ट फीस नहीं लगती।"
            ),
            costAndSpeed = BilingualText("Zero Court Fee | Settled in 1–3 hearings", "कोई कोर्ट फीस नहीं | 1–3 सुनवाई में निपटारा"),
            isCourtProceeding = false,
            officialPortalOrContact = "Contact District Court DLSA | Helpline 15100",
            whenToChoose = BilingualText(
                "Best for electricity/water/telecom public utility disputes (Permanent Lok Adalat) or when both parties want a fast, final settlement without appeals.",
                "बिजली/पानी/टेलीकॉम जैसी जनोपयोगी सेवाओं के विवादों या आपसी समझौते से त्वरित समाधान के लिए सर्वोत्तम।"
            )
        ),
        AuthorityComparisonItem(
            id = "mediation_adr",
            name = BilingualText("Mediation & Conciliation Centre", "मध्यस्थता एवं सुलह केंद्र (Mediation Centre)"),
            bestForIssues = listOf("Rental & Deposit Disputes", "Family & Matrimonial", "Workplace & Partnership Disputes", "Neighbour Disputes"),
            simpleExplanation = BilingualText(
                "A trained neutral mediator helps both sides reach a confidential, mutually acceptable written settlement agreement (governed by the Mediation Act, 2023 and CPC Section 89).",
                "एक प्रशिक्षित निष्पक्ष मध्यस्थ दोनों पक्षों को गोपनीय और आपसी सहमति से लिखित समझौते तक पहुंचने में मदद करता है (मध्यस्थता अधिनियम, 2023)।"
            ),
            costAndSpeed = BilingualText("Free or nominal at Court/DLSA Centres | 2–6 weeks", "न्यायालय/DLSA केंद्रों पर निःशुल्क या नाममात्र शुल्क | 2–6 सप्ताह"),
            isCourtProceeding = false,
            officialPortalOrContact = "District Court Mediation Centre / DLSA",
            whenToChoose = BilingualText(
                "Choose when you want to preserve relationships, maintain privacy, and avoid years of adversarial court litigation.",
                "जब आप गोपनीयता बनाए रखना चाहते हैं और वर्षों की अदालती लड़ाई से बचना चाहते हैं।"
            )
        ),
        AuthorityComparisonItem(
            id = "pgportal",
            name = BilingualText("CPGRAMS / Government Grievance Portal", "CPGRAMS / सरकारी जन शिकायत पोर्टल"),
            bestForIssues = listOf("Government Department Delays", "Passport/Pension/EPFO/Railways", "Public Service Delivery Issues"),
            simpleExplanation = BilingualText(
                "Centralized Public Grievance Redress and Monitoring System (pgportal.gov.in) connecting citizens directly to Central and State Government ministries.",
                "केंद्रीकृत लोक शिकायत निवारण और निगरानी प्रणाली (pgportal.gov.in) जो नागरिकों को सीधे केंद्र और राज्य मंत्रालयों से जोड़ती है।"
            ),
            costAndSpeed = BilingualText("Free | 21–30 days statutory target", "निःशुल्क | 21–30 दिन"),
            isCourtProceeding = false,
            officialPortalOrContact = "https://pgportal.gov.in",
            whenToChoose = BilingualText(
                "Choose for grievances against government departments, public sector banks, railways, post offices, or public authorities.",
                "सरकारी विभागों, सार्वजनिक क्षेत्र के बैंकों, रेलवे या डाकघर के खिलाफ शिकायतों के लिए चुनें।"
            )
        ),
        AuthorityComparisonItem(
            id = "cyber_police",
            name = BilingualText("Cybercrime Reporting & Police Channels", "साइबर क्राइम रिपोर्टिंग और पुलिस तंत्र"),
            bestForIssues = listOf("Online Financial Fraud", "Identity Theft / Hacking", "Physical Assault / Theft / Cognizable Crimes"),
            simpleExplanation = BilingualText(
                "Law enforcement channels responsible for investigating criminal offences, freezing fraudulent accounts, and filing charge sheets before Criminal Courts.",
                "आपराधिक मामलों की जांच करने, धोखाधड़ी वाले खातों को फ्रीज करने और न्यायालय में चार्जशीट दाखिल करने वाला पुलिस तंत्र।"
            ),
            costAndSpeed = BilingualText("Free | Immediate reporting required", "निःशुल्क | तत्काल रिपोर्टिंग आवश्यक"),
            isCourtProceeding = false,
            officialPortalOrContact = "1930 (Cyber) | 112 (Emergency) | https://cybercrime.gov.in",
            whenToChoose = BilingualText(
                "Choose whenever a criminal offence (fraud, theft, violence, extortion) has occurred rather than a purely civil contract disagreement.",
                "जब केवल सिविल विवाद न होकर कोई आपराधिक घटना (ठगी, चोरी, हिंसा, ब्लैकमेलिंग) हुई हो।"
            )
        )
    )

    val verifiedHelplines = listOf(
        HelplineItem(
            id = "emergency_112",
            number = "112",
            title = BilingualText(
                "National Emergency Response Support System (ERSS)",
                "राष्ट्रीय आपातकालीन प्रतिक्रिया प्रणाली (112)"
            ),
            purpose = BilingualText(
                "Unified 24x7 pan-India emergency number for immediate Police, Fire, Ambulance, and physical safety danger.",
                "तत्काल पुलिस, फायर, एम्बुलेंस और शारीरिक सुरक्षा संकट के लिए 24x7 अखिल भारतीय आपातकालीन नंबर।"
            ),
            limitationsAndScope = BilingualText(
                "For active emergencies and immediate danger to life/property. Do not use for routine civil legal advice.",
                "केवल तत्काल खतरे और आपातकालीन पुलिस/चिकित्सा सहायता के लिए। सामान्य कानूनी सलाह के लिए उपयोग न करें।"
            ),
            officialPortal = "https://112.gov.in",
            isImmediateEmergency = true,
            lastVerifiedNote = BilingualText("Verified Official Pan-India Emergency Number (MHA)", "सत्यापित आधिकारिक राष्ट्रीय आपातकालीन नंबर (गृह मंत्रालय)")
        ),
        HelplineItem(
            id = "nalsa_15100",
            number = "15100",
            title = BilingualText(
                "NALSA National Legal Aid Helpline",
                "राष्ट्रीय विधिक सेवा प्राधिकरण (NALSA) हेल्पलाइन"
            ),
            purpose = BilingualText(
                "Toll-free legal assistance helpline operated by NALSA and State Legal Services Authorities to connect citizens with free legal advice and DLSA Front Offices.",
                "नागरिकों को निःशुल्क कानूनी सलाह और जिला विधिक सेवा प्राधिकरण (DLSA) से जोड़ने के लिए NALSA की टोल-फ्री हेल्पलाइन।"
            ),
            limitationsAndScope = BilingualText(
                "Provides legal aid guidance and connects to State/District Legal Services Authorities. In Delhi, DSLSA also operates dedicated helpline 1516.",
                "कानूनी सहायता मार्गदर्शन प्रदान करता है और राज्य/जिला विधिक सेवा प्राधिकरण से जोड़ता है (दिल्ली में 1516 भी उपलब्ध है)।"
            ),
            officialPortal = "https://nalsa.gov.in",
            isImmediateEmergency = false,
            lastVerifiedNote = BilingualText("Verified Statutory Legal Aid Number (nalsa.gov.in)", "सत्यापित वैधानिक विधिक सहायता नंबर (nalsa.gov.in)")
        ),
        HelplineItem(
            id = "cyber_1930",
            number = "1930",
            title = BilingualText(
                "National Cyber Crime Helpline (Financial Fraud)",
                "राष्ट्रीय साइबर अपराध हेल्पलाइन (वित्तीय धोखाधड़ी)"
            ),
            purpose = BilingualText(
                "Immediate reporting of online financial fraud, UPI scams, unauthorized bank transfers, and cyber extortion.",
                "ऑनलाइन वित्तीय धोखाधड़ी, UPI ठगी और अनधिकृत बैंक ट्रांसफर की तत्काल रिपोर्टिंग के लिए।"
            ),
            limitationsAndScope = BilingualText(
                "After calling 1930, you must also complete formal complaint submission on cybercrime.gov.in with transaction details.",
                "1930 पर कॉल करने के बाद, आपको cybercrime.gov.in पर ट्रांजेक्शन विवरण के साथ औपचारिक शिकायत भी पूरी करनी होगी।"
            ),
            officialPortal = "https://cybercrime.gov.in",
            isImmediateEmergency = true,
            lastVerifiedNote = BilingualText("Verified MHA / I4C Helpline (Replaced old 155260)", "सत्यापित गृह मंत्रालय साइबर हेल्पलाइन")
        ),
        HelplineItem(
            id = "women_181",
            number = "181",
            title = BilingualText(
                "Women Helpline (All-India Women in Distress)",
                "महिला हेल्पलाइन (संकटग्रस्त महिलाओं के लिए)"
            ),
            purpose = BilingualText(
                "24x7 support for women facing domestic violence, harassment, abuse, or needing One Stop Centre (Sakhi) shelter and legal referral.",
                "घरेलू हिंसा, उत्पीड़न का सामना कर रही महिलाओं के लिए 24x7 सहायता और वन स्टॉप सेंटर (सखी) रेफरल।"
            ),
            limitationsAndScope = BilingualText(
                "Connects to State Women Helpline and Protection Officers. National Commission for Women (NCW) also operates 7827170170.",
                "राज्य महिला हेल्पलाइन और संरक्षण अधिकारियों से जोड़ता है। राष्ट्रीय महिला आयोग (NCW) हेल्पलाइन: 7827170170।"
            ),
            officialPortal = "https://ncw.nic.in",
            isImmediateEmergency = true,
            lastVerifiedNote = BilingualText("Verified Ministry of Women & Child Development Helpline", "सत्यापित महिला एवं बाल विकास मंत्रालय हेल्पलाइन")
        ),
        HelplineItem(
            id = "child_1098",
            number = "1098",
            title = BilingualText(
                "Childline India (Children in Need of Care & Protection)",
                "चाइल्डलाइन इंडिया (बच्चों की सुरक्षा एवं सहायता)"
            ),
            purpose = BilingualText(
                "24x7 national toll-free emergency phone outreach service for children in distress, child labour, abuse, or missing children.",
                "संकट में फंसे बच्चों, बाल श्रम, दुर्व्यवहार या लापता बच्चों की मदद के लिए 24x7 राष्ट्रीय टोल-फ्री सेवा।"
            ),
            limitationsAndScope = BilingualText(
                "Strictly for protection and welfare of minors (under 18 years of age) under the Juvenile Justice Act.",
                "किशोर न्याय अधिनियम के तहत केवल 18 वर्ष से कम आयु के बच्चों की सुरक्षा और कल्याण के लिए।"
            ),
            officialPortal = "https://wcd.nic.in",
            isImmediateEmergency = true,
            lastVerifiedNote = BilingualText("Verified WCD / Childline 1098", "सत्यापित चाइल्डलाइन 1098")
        ),
        HelplineItem(
            id = "consumer_1915",
            number = "1915",
            title = BilingualText(
                "National Consumer Helpline (NCH)",
                "राष्ट्रीय उपभोक्ता हेल्पलाइन (NCH)"
            ),
            purpose = BilingualText(
                "Guidance and docket registration for consumer disputes, defective goods, e-commerce refunds, and service deficiencies.",
                "उपभोक्ता विवादों, खराब सामान, ई-कॉमर्स रिफंड और सेवा में कमी के लिए शिकायत पंजीकरण और मार्गदर्शन।"
            ),
            limitationsAndScope = BilingualText(
                "Operates 8:00 AM to 8:00 PM (All 7 days except national holidays). Pre-litigation convergence platform, not a court.",
                "सुबह 8:00 से रात 8:00 बजे तक उपलब्ध। यह मुकदमे से पूर्व समाधान का मंच है, न्यायालय नहीं।"
            ),
            officialPortal = "https://consumerhelpline.gov.in",
            isImmediateEmergency = false,
            lastVerifiedNote = BilingualText("Verified Dept. of Consumer Affairs Helpline", "सत्यापित उपभोक्ता मामले विभाग हेल्पलाइन")
        ),
        HelplineItem(
            id = "senior_14567",
            number = "14567",
            title = BilingualText(
                "Elderline – National Helpline for Senior Citizens",
                "एल्डरलाइन – वरिष्ठ नागरिकों के लिए राष्ट्रीय हेल्पलाइन"
            ),
            purpose = BilingualText(
                "Support for senior citizens regarding pension issues, maintenance under Senior Citizens Act 2007, abuse prevention, and legal aid.",
                "वरिष्ठ नागरिकों के लिए पेंशन, भरण-पोषण अधिनियम 2007, दुर्व्यवहार से बचाव और कानूनी मार्गदर्शन।"
            ),
            limitationsAndScope = BilingualText(
                "Provides guidance and connects to District Social Welfare Officers and DLSA senior citizen cells.",
                "जिला समाज कल्याण अधिकारी और DLSA वरिष्ठ नागरिक प्रकोष्ठ से जोड़ता है।"
            ),
            officialPortal = "https://socialjustice.gov.in",
            isImmediateEmergency = false,
            lastVerifiedNote = BilingualText("Verified Ministry of Social Justice & Empowerment Helpline", "सत्यापित सामाजिक न्याय एवं अधिकारिता मंत्रालय हेल्पलाइन")
        )
    )
}
