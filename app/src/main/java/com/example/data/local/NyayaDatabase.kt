package com.example.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "lawyer_requests")
data class LawyerRequestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val referenceNumber: String,
    val userName: String,
    val mobileNumber: String,
    val state: String,
    val district: String,
    val city: String,
    val locality: String,
    val problemType: String,
    val description: String,
    val preferredLanguage: String,
    val assistanceType: String,
    val freeAidEligible: Boolean,
    val freeAidCriteria: String,
    val consentGiven: Boolean,
    val status: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "saved_complaints")
data class SavedComplaintEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val complainantName: String,
    val respondentName: String,
    val authorityForum: String,
    val incidentDate: String,
    val draftText: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "refund_cases")
data class RefundCaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val referenceNumber: String,
    val sellerName: String,
    val transactionId: String,
    val paymentDate: String,
    val amountInr: String,
    val issueSummary: String,
    val noticeDraft: String,
    val status: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "legal_deadlines")
data class LegalDeadlineEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val caseTitle: String,
    val caseType: String,
    val incidentOrOrderDate: String,
    val establishedDeadlineDate: String,
    val isDeadlineConfirmedByLawyer: Boolean,
    val documentsChecklist: String,
    val reminderNote: String,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "adr_requests")
data class AdrRequestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val referenceNumber: String,
    val disputeAbout: String,
    val partiesInvolved: String,
    val locationStateDistrict: String,
    val preferredAdrMode: String,
    val needsClinicHelp: Boolean,
    val statusNote: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Dao
interface NyayaDao {
    @Query("SELECT * FROM lawyer_requests ORDER BY createdAt DESC")
    fun getAllLawyerRequests(): Flow<List<LawyerRequestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLawyerRequest(request: LawyerRequestEntity)

    @Query("SELECT * FROM saved_complaints ORDER BY createdAt DESC")
    fun getAllComplaints(): Flow<List<SavedComplaintEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComplaint(complaint: SavedComplaintEntity)

    @Query("DELETE FROM saved_complaints WHERE id = :id")
    suspend fun deleteComplaint(id: Int)

    @Query("SELECT * FROM refund_cases ORDER BY createdAt DESC")
    fun getAllRefundCases(): Flow<List<RefundCaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRefundCase(refundCase: RefundCaseEntity)

    @Query("DELETE FROM refund_cases WHERE id = :id")
    suspend fun deleteRefundCase(id: Int)

    @Query("SELECT * FROM legal_deadlines ORDER BY createdAt DESC")
    fun getAllDeadlines(): Flow<List<LegalDeadlineEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeadline(deadline: LegalDeadlineEntity)

    @Query("UPDATE legal_deadlines SET isCompleted = :completed WHERE id = :id")
    suspend fun updateDeadlineCompleted(id: Int, completed: Boolean)

    @Query("DELETE FROM legal_deadlines WHERE id = :id")
    suspend fun deleteDeadline(id: Int)

    @Query("SELECT * FROM adr_requests ORDER BY createdAt DESC")
    fun getAllAdrRequests(): Flow<List<AdrRequestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdrRequest(request: AdrRequestEntity)

    @Query("DELETE FROM lawyer_requests")
    suspend fun clearAllLawyerRequests()

    @Query("DELETE FROM saved_complaints")
    suspend fun clearAllComplaints()

    @Query("DELETE FROM refund_cases")
    suspend fun clearAllRefundCases()

    @Query("DELETE FROM legal_deadlines")
    suspend fun clearAllDeadlines()

    @Query("DELETE FROM adr_requests")
    suspend fun clearAllAdrRequests()
}

@Database(
    entities = [
        LawyerRequestEntity::class,
        SavedComplaintEntity::class,
        RefundCaseEntity::class,
        LegalDeadlineEntity::class,
        AdrRequestEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NyayaDatabase : RoomDatabase() {
    abstract fun nyayaDao(): NyayaDao

    companion object {
        @Volatile
        private var INSTANCE: NyayaDatabase? = null

        fun getInstance(context: Context): NyayaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NyayaDatabase::class.java,
                    "nyaya_sahayak_db"
                ).fallbackToDestructiveMigration(dropAllTables = true).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
