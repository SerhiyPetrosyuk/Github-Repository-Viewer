package com.mlsdev.serhiy.domain;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowersUseCase;
import com.mlsdev.serhiy.domain.interactor.implementation.GetFollowersUseCaseImpl;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository.RepositoryCallBack;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class GetFollowersUseCaseImplTest extends TestCase {

    public static String ERROR_MESSAGE = "error";
    private final String username = "SerhiyPetrosyuk";
    private final int followers = 12674;
    private GetFollowersUseCase mGetFollowersUseCase;

    @Mock
    private GithubUserRepository mockRepository;
    @Mock
    private RepositoryCallBack<Integer> mockFollowersCallback;

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
                RepositoryCallBack<Integer> followersCallback = (RepositoryCallBack<Integer>) invocation.getArguments()[0];
                followersCallback.onSuccess(followers);
                return null;
            }
        }).when(mockRepository).getFollowersNumber(username, mockFollowersCallback);
    }

    @Test
    public void testOnErrorRepositoryCallback() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                RepositoryCallBack<Integer> followersCallback = (RepositoryCallBack<Integer>) invocation.getArguments()[0];
                followersCallback.onError(ERROR_MESSAGE);
                return null;
            }
        }).when(mockRepository).getFollowersNumber(username, mockFollowersCallback);
    }

}
