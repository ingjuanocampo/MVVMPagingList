package juanocampo.myapplication.com.commons

interface IMapper<in R, out T> {
    operator fun invoke(toParse: R) : T
}