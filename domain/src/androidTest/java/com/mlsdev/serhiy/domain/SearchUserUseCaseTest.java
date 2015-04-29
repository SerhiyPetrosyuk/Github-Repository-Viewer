package com.mlsdev.serhiy.domain;

import com.mlsdev.serhiy.domain.interactor.abstraction.SearchUserUseCase;
import com.mlsdev.serhiy.domain.interactor.implementation.SearchUserUseCaseImpl;
import com.mlsdev.serhiy.domain.model.GithubUser;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static com.mlsdev.serhiy.domain.repository.GithubUserRepository.GithubUserCallback;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by Serhiy Petrosyuk on 28.04.15.
 */
public class SearchUserUseCaseTest extends TestCase {

    private final String username = "SerhiyPetrosyuk";
    private SearchUserUseCase mSearchUserUseCase;

    @Mock
    private GithubUserRepository mockRepository;
    @Mock
    private GithubUserCallback mockUserCallback;

    @Before
    public void setUp() {
        mSearchUserUseCase = new SearchUserUseCaseImpl(mockRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteUseCase() {
        assertNotNull(mSearchUserUseCase);
        assertNotNull(mockRepository);
        assertNotNull(mockUserCallback);

        mockRepository.searchGithubUserByName(username, mockUserCallback);
        verify(mockRepository).searchGithubUserByName(username, mockUserCallback);
    }

    @Test
    public void testOnSuccessRepositoryCallback(){
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                GithubUserCallback githubUserCallback = (GithubUserCallback) invocation.getArguments()[0];
                githubUserCallback.onUserLoaded(any(GithubUser.class));
                return null;
            }
        }).when(mockRepository).searchGithubUserByName(username, mockUserCallback);
    }

    @Test
    public void testOnErrorRepositoryCallback(){
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                GithubUserCallback githubUserCallback = (GithubUserCallback) invocation.getArguments()[0];
                githubUserCallback.onError();
                return null;
            }
        }).when(mockRepository).searchGithubUserByName(username, mockUserCallback);
    }

}
