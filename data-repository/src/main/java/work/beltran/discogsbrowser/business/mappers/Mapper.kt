package work.beltran.discogsbrowser.business.mappers

interface Mapper<TypeOne, TypeTwo> {
    fun mapTo(typeOne: TypeOne): TypeTwo
    fun mapFrom(typeTwo: TypeTwo): TypeOne
    fun mapTo(list: List<TypeOne>): List<TypeTwo> = list.map(this::mapTo)
    fun mapFrom(list: List<TypeTwo>): List<TypeOne> = list.map(this::mapFrom)
}