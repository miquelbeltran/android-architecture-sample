package work.beltran.discogsbrowser.business.wanted

import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import work.beltran.discogsbrowser.test.RECORD
import work.beltran.discogsbrowser.test.RECORD_ID
import work.beltran.discogsbrowser.test.TestSchedulers
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.model.UserWanted
import work.beltran.discogsbrowser.api.model.pagination.Pagination
import work.beltran.discogsbrowser.business.SettingsRepository
import work.beltran.discogsbrowser.business.WantedInteractor

@RunWith(MockitoJUnitRunner::class)
class WantedInteractorImplTest {

    lateinit var wantedInteractor: WantedInteractor

    @Mock
    lateinit var service: DiscogsService
    @Mock
    lateinit var settings: SettingsRepository

    @Before
    fun setUp() {
        wantedInteractor = WantedInteractorImpl(service, TestSchedulers, settings)
        Mockito.`when`(settings.getUsername()).thenReturn(USERNAME)
    }

    @Test
    fun `load wanted records from user account`() {
        `when`(service.getWantedList(USERNAME, PAGE)).thenReturn(Single.just(UserWanted(
                pagination = Pagination(PAGE, 1),
                records = listOf(RECORD))))
        val tester = wantedInteractor.getWanted(PAGE).test()
        tester.assertNoErrors()
        tester.assertComplete()
        val value = tester.values()[0]
        assertThat(value.records[0].id).isEqualTo(RECORD_ID)
    }

    companion object {
        private const val USERNAME = "username"
        private const val PAGE = 0
    }
}

