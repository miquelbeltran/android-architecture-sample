package work.beltran.discogsbrowser.business.profile

import io.reactivex.Single
import org.junit.Before

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.model.UserIdentity
import work.beltran.discogsbrowser.api.model.UserProfile
import work.beltran.discogsbrowser.business.ProfileInteractor
import work.beltran.discogsbrowser.business.SettingsRepository
import work.beltran.discogsbrowser.test.TestSchedulers

class ProfileInteractorImplTest {

    lateinit var profileInteractor: ProfileInteractor

    @Mock
    lateinit var service: DiscogsService
    @Mock
    lateinit var settings: SettingsRepository

    private val profile = UserProfile(
            username = NAME,
            numCollection = 1,
            numWantlist = 2,
            avatarUrl = "url")
    private val identity = UserIdentity(
            id = 1,
            username = NAME)

    @Before
    fun setUp() {
        profileInteractor = ProfileInteractorImpl(service = service,
                schedulers = TestSchedulers,
                settings = settings)
        Mockito.`when`(service.userIdentity).thenReturn(Single.just(identity))
        Mockito.`when`(service.getUserProfile(NAME)).thenReturn(Single.just(profile))
        Mockito.`when`(settings.getUsername()).thenReturn(NAME)
    }

    @Test
    fun `get identity`() {
        val tester = profileInteractor.identity.test()
        tester.assertNoErrors()
        tester.assertComplete()
        assertThat(tester.values()[0]).isEqualTo(identity)
    }

    @Test
    fun `get profile`() {
        val tester = profileInteractor.profile.test()
        tester.assertNoErrors()
        tester.assertComplete()
        assertThat(tester.values()[0]).isEqualTo(profile)
    }

    companion object {
        private const val NAME = "NAME"
    }
}