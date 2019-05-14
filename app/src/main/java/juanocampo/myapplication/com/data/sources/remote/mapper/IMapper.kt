package juanocampo.myapplication.com.data.sources.remote.mapper

interface IMapper<in R, out T> {
    fun mapResponseToAppModel(toParse: R) : T
}