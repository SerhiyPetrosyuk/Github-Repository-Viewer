package com.mlsdev.serhiy.domain;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowersUseCase;
import com.mlsdev.serhiy.domain.interactor.implementation.GetFollowersUseCaseImpl;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static com.mlsdev.serhiy.domain.repository.GithubUserRepository.FollowersCallback;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class GetFollowersUseCaseImplTest extends TestCase {

    private final String username = "SerhiyPetrosyuk";
    private final int followers = 12674;
    private GetFollowersUseCase mGetFollowersUseCase;

    @Mock
    private GithubUserRepository mockRepository;
    @Mock
    private FollowersCallback mockFollowersCallback;

    @Before
    public void setUp() throws Exception {
        mGetFollowersUseCase = new GetFollowersUseCaseImpl(mockRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCallUseCaseExecuteMethod() {
        assertNotNull(mGetFollowersUseCase);
        assertNotNull(mockFollowersCallback);
        assertNotNull(mockRepository);

        mockRepository.getFollowersNumber(username, mockFollowersCallback);
        verify(mockRepository).getFollowersNumber(username, mockFollowersCallback);
    }

    @Test
    public void testOnSuccessRepositoryCallback() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                FollowersCallback followersCallback = (FollowersCallback) invocation.getArguments()[0];
                followersCallback.onFollowersLoaded(followers);
                return null;
            }
        }).when(mockRepository).getFollowersNumber(username, mockFollowersCallback);
    }

    @Test
    public void testOnErrorRepositoryCallback() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                FollowersCallback followersCallback = (FollowersCallback) invocation.getArguments()[0];
                followersCallback.onError();
                return null;
            }
        }).when(mockRepository).getFollowersNumber(username, mockFollowersCallback);
    }

}
