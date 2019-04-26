package juanocampo.myapplication.com.model.sources.remote.mapper

interface IMapper<in R, out T> {
    fun mapResponseToAppModel(toParse: R) : T
}