package com.mlsdev.serhiy.domain;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowingsUseCase;
import com.mlsdev.serhiy.domain.interactor.implementation.GetFollowingsUseCaseImpl;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class GetFollowingsUseCaseImplTest extends TestCase {

    private final String username = "SerhiyPetrosyuk";
    private final int followers = 12674;
    private GetFollowingsUseCase mGetFollowersUseCase;

    @Mock
    private GithubUserRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        mGetFollowersUseCase = new GetFollowingsUseCaseImpl(mockRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCallUseCaseExecuteMethod() {
        assertNotNull(mGetFollowersUseCase);
        assertNotNull(mockRepository);

        mockRepository.getFollowingsNumber(username);
        verify(mockRepository).getFollowingsNumber(username);
    }

}
