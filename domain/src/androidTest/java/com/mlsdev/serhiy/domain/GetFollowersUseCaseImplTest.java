package com.mlsdev.serhiy.domain;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowersUseCase;
import com.mlsdev.serhiy.domain.interactor.implementation.GetFollowersUseCaseImpl;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class GetFollowersUseCaseImplTest extends TestCase {

    public static String ERROR_MESSAGE = "error";
    private final String username = "SerhiyPetrosyuk";
    private final int followers = 12674;
    private GetFollowersUseCase mGetFollowersUseCase;

    @Mock
    private GithubUserRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        mGetFollowersUseCase = new GetFollowersUseCaseImpl(mockRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCallUseCaseExecuteMethod() {
        assertNotNull(mGetFollowersUseCase);
        assertNotNull(mockRepository);

        mockRepository.getFollowersNumber(username);
        verify(mockRepository).getFollowersNumber(username);
    }

}
