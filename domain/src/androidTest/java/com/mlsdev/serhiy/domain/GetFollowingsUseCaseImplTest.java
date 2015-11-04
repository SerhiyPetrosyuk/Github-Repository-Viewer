package com.mlsdev.serhiy.domain;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowingsUseCase;
import com.mlsdev.serhiy.domain.interactor.implementation.GetFollowingsUseCaseImpl;
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

public class GetFollowingsUseCaseImplTest extends TestCase {

    private final String username = "SerhiyPetrosyuk";
    private final int followers = 12674;
    private GetFollowingsUseCase mGetFollowersUseCase;

    @Mock private GithubUserRepository mockRepository;
    @Mock private RepositoryCallBack<Integer> mockFollowingsCallback;

    @Before
    public void setUp() throws Exception {
        mGetFollowersUseCase = new GetFollowingsUseCaseImpl(mockRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCallUseCaseExecuteMethod(){
        assertNotNull(mGetFollowersUseCase);
        assertNotNull(mockFollowingsCallback);
        assertNotNull(mockRepository);

        mockRepository.getFollowingsNumber(username, mockFollowingsCallback);
        verify(mockRepository).getFollowingsNumber(username, mockFollowingsCallback);
    }

    @Test
    public void testOnSuccessRepositoryCallback(){
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                RepositoryCallBack<Integer> callBack = (RepositoryCallBack<Integer>) invocation.getArguments()[0];
                callBack.onSuccess(followers);
                return null;
            }
        }).when(mockRepository).getFollowingsNumber(username, mockFollowingsCallback);
    }

    @Test
    public void testOnErrorRepositoryCallback(){
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                RepositoryCallBack<Integer> callBack = (RepositoryCallBack<Integer>) invocation.getArguments()[0];
                callBack.onError("error");
                return null;
            }
        }).when(mockRepository).getFollowingsNumber(username, mockFollowingsCallback);
    }

}
