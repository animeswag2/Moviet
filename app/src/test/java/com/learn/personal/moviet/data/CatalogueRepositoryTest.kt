package com.learn.personal.moviet.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learn.personal.moviet.utils.DataDummy
import com.learn.personal.moviet.utils.LiveDataTestUtil

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import org.mockito.Mockito

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote)

    private val movieResponses = DataDummy.generateMoviesList()
    private val movieId = movieResponses[0].id
    private val tvshowResponses = DataDummy.generateTvShowsList()
    private val tvshowId = tvshowResponses[0].id

    @Test
    suspend fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                    .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(catalogueRepository.getMovies())
        verify<RemoteDataSource>(remote).getMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getMovieDetail() {
    }

    @Test
    fun getTvShows() {
    }

    @Test
    fun getTvShowDetail() {
    }
}