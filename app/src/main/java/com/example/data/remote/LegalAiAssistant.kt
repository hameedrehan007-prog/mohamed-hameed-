package com.example.data.remote

import com.example.BuildConfig
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

@JsonClass(generateAdapter = false)
data class GeminiPart(val text: String? = null)

@JsonClass(generateAdapter = false)
data class GeminiContent(
    val role: String? = "user",
    val parts: List<GeminiPart>
)

@JsonClass(generateAdapter = false)
data class GeminiGenerationConfig(
    val temperature: Float = 0.2f
)

@JsonClass(generateAdapter = false)
data class GeminiRequest(
    val contents: List<GeminiContent>,
    val systemInstruction: GeminiContent? = null,
    val generationConfig: GeminiGenerationConfig? = GeminiGenerationConfig(0.2f)
)

@JsonClass(generateAdapter = false)
data class GeminiCandidate(
    val content: GeminiContent? = null
)

@JsonClass(generateAdapter = false)
data class GeminiResponse(
    val candidates: List<GeminiCandidate>? = null
)

interface GeminiLegalService {
    @POST("v1beta/models/gemini-3.5-flash:generateContent")
    suspend fun generateLegalGuidance(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}

object LegalAiAssistant {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val api: GeminiLegalService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GeminiLegalService::class.java)
    }

    fun isApiKeyConfigured(): Boolean {
        val key = BuildConfig.GEMINI_API_KEY
        return key.isNotBlank() && key != "MY_GEMINI_API_KEY" && !key.startsWith("YOUR_")
    }

    suspend fun askLegalInformation(
        userQuestion: String,
        languageCode: String
    ): Result<String> = withContext(Dispatchers.IO) {
        if (!isApiKeyConfigured()) {
            return@withContext Result.failure(
                IllegalStateException("Gemini API key is not configured in Secrets. Using verified offline Indian legal database.")
            )
        }

        val langInstruction = if (languageCode == "hi") {
            "Respond entirely in simple, everyday Hindi (देवनागरी) so an ordinary citizen in India can understand easily."
        } else {
            "Respond in clear, simple English suitable for an ordinary citizen in India without legal background."
        }

        val systemPrompt = """
            You are NYAYA SAHAYAK, an Indian Legal Information Assistant designed for ordinary citizens in India.
            STRICT RULES:
            1. $langInstruction
            2. Provide structured sections:
               - Simple Explanation
               - Applicable Rights & Verified Indian Laws (cite Bharatiya Nyaya Sanhita 2023, Bharatiya Nagarik Suraksha Sanhita 2023, Consumer Protection Act 2019, IT Act 2000, Legal Services Authorities Act 1987, etc. ONLY when certain)
               - Practical Next Steps
               - Documents to Preserve
               - Where to Seek Help (e.g., NALSA 15100, NCH 1915, Cybercrime 1930, DLSA)
            3. NEVER invent sections, case citations, or lawyer names.
            4. Always include a brief note that this is general legal information and not a substitute for advice from a qualified advocate, and that facts/jurisdiction/limitation dates may affect the outcome.
        """.trimIndent()

        try {
            val response = api.generateLegalGuidance(
                apiKey = BuildConfig.GEMINI_API_KEY,
                request = GeminiRequest(
                    contents = listOf(GeminiContent(role = "user", parts = listOf(GeminiPart(userQuestion)))),
                    systemInstruction = GeminiContent(role = "system", parts = listOf(GeminiPart(systemPrompt)))
                )
            )
            val text = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
            if (!text.isNullOrBlank()) {
                Result.success(text)
            } else {
                Result.failure(Exception("Empty response from AI service"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
