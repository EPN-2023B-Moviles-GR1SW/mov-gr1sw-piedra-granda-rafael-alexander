interface GenericDAO<T, ID> {
    fun create(entity: T)
    fun update(id: ID, entity: T)
    fun delete(id: ID)
    fun getById(id: ID)
}