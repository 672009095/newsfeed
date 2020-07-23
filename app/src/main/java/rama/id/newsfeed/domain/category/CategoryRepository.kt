package rama.id.newsfeed.domain.category

interface CategoryRepository {
    suspend fun getListOfCategory(category: String?): List<Category>
}