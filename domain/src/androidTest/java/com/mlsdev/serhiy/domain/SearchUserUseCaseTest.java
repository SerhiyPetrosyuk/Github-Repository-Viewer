package com.mlsdev.serhiy.domain;

import com.mlsdev.serhiy.domain.interactor.abstraction.SearchUserUseCase;
import com.mlsdev.serhiy.domain.interactor.implementation.SearchUserUseCaseImpl;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by Serhiy Petrosyuk on 28.04.15.
 */
public class SearchUserUseCaseTest extends TestCase {

    private final String username = "SerhiyPetrosyuk";
    private SearchUserUseCase mSearchUserUseCase;

    @Mock
    private GithubUserRepository mockRepository;

    @Before
    public void setUp() {
        mSearchUserUseCase = new SearchUserUseCaseImpl(mockRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteUseCase() {
        assertNotNull(mSearchUserUseCase);
        assertNotNull(mockRepository);

        mockRepository.searchGithubUserByName(username);
        verify(mockRepository).searchGithubUserByName(username);
    }

}
