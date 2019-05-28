package juanocampo.myapplication.com.commons

interface IMapper<in R, out T> {
    fun invoke(toParse: R) : T
}