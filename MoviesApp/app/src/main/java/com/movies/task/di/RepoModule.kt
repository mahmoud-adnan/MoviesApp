package  com.movies.task.di


import com.movies.task.data.MoviesRepository
import com.movies.task.data.sources.remoteApi.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(service: ApiService)
            = MoviesRepository(service)





}



