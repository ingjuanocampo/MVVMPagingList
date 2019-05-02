# MVVMPagingList


Architecture: MVVM 
Technologies: Dagger2, Retrofit, Coroutines, ViewModel, LiveData, Diffutils.  
Language: Kotlin 

This project has the purpose of exposing good practices about Android, Kotlin and MVVM Architecture. 

The application will fetch some initial items from the https://developers.themoviedb.org/ API, then those items will be load into the view. While the user scroll towards the end of the list, the application will load more items (Paging as infinity list). 

The application follows the MVVM architecture by using: 

RemoteDataSource: In charge of the coordination of the remote source, in this case, the API of https://developers.themoviedb.org/ which is consumed using a simple retrofit implementation. 

Repository: Uses the available data sources to pull the items and present the info in different states, SUCCESS, LOADING, ERROR. 
 
RepositoryModule: Is the module in charge to provide Dependency injection to the package of `repository`, within an `ApplicationScope` which is defined as a Global scope of the app, equivalent to Singleton. 

MovieViewModel: Uses Coroutines as threading approach (non-blocking threading), the idea is to operate all the synchronization with the repository in the background(IO thread) and then present the information in the UI (Main Thread), please follow the annotation to understand in which thread the method is executed. In another hand, the ViewModel present the information to the UI via `LiveData`. 

For the structure of the application, I used Dagger to provide scoped dependency injections across the classes. ApplicationModule only is in charge to bind the context. RepositoryModule provides the definitions and scope of the classes into the model layer. MovieAppComponent recompiles the above modules and exposed the dependencies needed outside the Model Layer. MovieListModule is a limited scope module to provide only the dependencies required to operate in the fragment. It has a lifecycle (limited scope MovieListScope) according to the UI. 

With the described structure we are able to have a Clean code application which is testable is its majority. Please check the testing done in the model layer. 

For the unit test, I only used Mockito to be able to mock the interface and test the features. There are simple tests to verify the results, interaction, and behavior of the business.

Please let me know if you have any doubts about this project. 
Contact: ing.juanocampo@gmail.com




