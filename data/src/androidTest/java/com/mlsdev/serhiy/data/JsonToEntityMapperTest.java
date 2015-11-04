package com.mlsdev.serhiy.data;

import com.mlsdev.serhiy.data.entity.mapper.EntityJsonMapper;
import com.mlsdev.serhiy.data.entity.repository.RepositoryEntity;
import com.mlsdev.serhiy.data.entity.user.Item;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by Serhiy Petrosyuk on 28.04.15.
 */
public class JsonToEntityMapperTest extends TestCase {

    // region json
    private final String userJson = "{" +
            "   \"total_count\":1," +
            "   \"incomplete_results\":false," +
            "   \"items\":[" +
            "       {\"login\":\"SerhiyPetrosyuk\"," +
            "           \"id\":10401275," +
            "           \"avatar_url\":\"https://avatars.githubusercontent.com/u/10401275?v=3\"," +
            "           \"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/SerhiyPetrosyuk\"," +
            "           \"html_url\":\"https://github.com/SerhiyPetrosyuk\"," +
            "           \"followers_url\":\"https://api.github.com/users/SerhiyPetrosyuk/followers\"," +
            "           \"following_url\":\"https://api.github.com/users/SerhiyPetrosyuk/following{/other_user}\"," +
            "           \"gists_url\":\"https://api.github.com/users/SerhiyPetrosyuk/gists{/gist_id}\"," +
            "           \"starred_url\":\"https://api.github.com/users/SerhiyPetrosyuk/starred{/owner}{/repo}\"," +
            "           \"subscriptions_url\":\"https://api.github.com/users/SerhiyPetrosyuk/subscriptions\"," +
            "           \"organizations_url\":\"https://api.github.com/users/SerhiyPetrosyuk/orgs\"," +
            "           \"repos_url\":\"https://api.github.com/users/SerhiyPetrosyuk/repos\"," +
            "           \"events_url\":\"https://api.github.com/users/SerhiyPetrosyuk/events{/privacy}\"," +
            "           \"received_events_url\":\"https://api.github.com/users/SerhiyPetrosyuk/received_events\"," +
            "           \"type\":\"User\"," +
            "           \"site_admin\":false," +
            "           \"score\":19.83452" +
            "       }" +
            "   ]" +
            "}";

    public final String repositoryJson = "[\n" +
            "  {\n" +
            "    \"id\": 34457222,\n" +
            "    \"name\": \"Github-Repository-Viewer\",\n" +
            "    \"full_name\": \"SerhiyPetrosyuk/Github-Repository-Viewer\",\n" +
            "    \"owner\": {\n" +
            "      \"login\": \"SerhiyPetrosyuk\",\n" +
            "      \"id\": 10401275,\n" +
            "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/10401275?v=3\",\n" +
            "      \"gravatar_id\": \"\",\n" +
            "      \"url\": \"https://api.github.com/users/SerhiyPetrosyuk\",\n" +
            "      \"html_url\": \"https://github.com/SerhiyPetrosyuk\",\n" +
            "      \"followers_url\": \"https://api.github.com/users/SerhiyPetrosyuk/followers\",\n" +
            "      \"following_url\": \"https://api.github.com/users/SerhiyPetrosyuk/following{/other_user}\",\n" +
            "      \"gists_url\": \"https://api.github.com/users/SerhiyPetrosyuk/gists{/gist_id}\",\n" +
            "      \"starred_url\": \"https://api.github.com/users/SerhiyPetrosyuk/starred{/owner}{/repo}\",\n" +
            "      \"subscriptions_url\": \"https://api.github.com/users/SerhiyPetrosyuk/subscriptions\",\n" +
            "      \"organizations_url\": \"https://api.github.com/users/SerhiyPetrosyuk/orgs\",\n" +
            "      \"repos_url\": \"https://api.github.com/users/SerhiyPetrosyuk/repos\",\n" +
            "      \"events_url\": \"https://api.github.com/users/SerhiyPetrosyuk/events{/privacy}\",\n" +
            "      \"received_events_url\": \"https://api.github.com/users/SerhiyPetrosyuk/received_events\",\n" +
            "      \"type\": \"User\",\n" +
            "      \"site_admin\": false\n" +
            "    },\n" +
            "    \"private\": false,\n" +
            "    \"html_url\": \"https://github.com/SerhiyPetrosyuk/Github-Repository-Viewer\",\n" +
            "    \"description\": \"\",\n" +
            "    \"fork\": false,\n" +
            "    \"url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer\",\n" +
            "    \"forks_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/forks\",\n" +
            "    \"keys_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/keys{/key_id}\",\n" +
            "    \"collaborators_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/collaborators{/collaborator}\",\n" +
            "    \"teams_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/teams\",\n" +
            "    \"hooks_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/hooks\",\n" +
            "    \"issue_events_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/issues/events{/number}\",\n" +
            "    \"events_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/events\",\n" +
            "    \"assignees_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/assignees{/user}\",\n" +
            "    \"branches_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/branches{/branch}\",\n" +
            "    \"tags_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/tags\",\n" +
            "    \"blobs_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/git/blobs{/sha}\",\n" +
            "    \"git_tags_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/git/tags{/sha}\",\n" +
            "    \"git_refs_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/git/refs{/sha}\",\n" +
            "    \"trees_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/git/trees{/sha}\",\n" +
            "    \"statuses_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/statuses/{sha}\",\n" +
            "    \"languages_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/languages\",\n" +
            "    \"stargazers_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/stargazers\",\n" +
            "    \"contributors_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/contributors\",\n" +
            "    \"subscribers_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/subscribers\",\n" +
            "    \"subscription_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/subscription\",\n" +
            "    \"commits_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/commits{/sha}\",\n" +
            "    \"git_commits_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/git/commits{/sha}\",\n" +
            "    \"comments_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/comments{/number}\",\n" +
            "    \"issue_comment_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/issues/comments{/number}\",\n" +
            "    \"contents_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/contents/{+path}\",\n" +
            "    \"compare_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/compare/{base}...{head}\",\n" +
            "    \"merges_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/merges\",\n" +
            "    \"archive_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/{archive_format}{/ref}\",\n" +
            "    \"downloads_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/downloads\",\n" +
            "    \"issues_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/issues{/number}\",\n" +
            "    \"pulls_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/pulls{/number}\",\n" +
            "    \"milestones_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/milestones{/number}\",\n" +
            "    \"notifications_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/notifications{?since,all,participating}\",\n" +
            "    \"labels_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/labels{/name}\",\n" +
            "    \"releases_url\": \"https://api.github.com/repos/SerhiyPetrosyuk/Github-Repository-Viewer/releases{/id}\",\n" +
            "    \"created_at\": \"2015-04-23T13:16:13Z\",\n" +
            "    \"updated_at\": \"2015-04-24T13:13:09Z\",\n" +
            "    \"pushed_at\": \"2015-04-24T13:13:08Z\",\n" +
            "    \"git_url\": \"git://github.com/SerhiyPetrosyuk/Github-Repository-Viewer.git\",\n" +
            "    \"ssh_url\": \"git@github.com:SerhiyPetrosyuk/Github-Repository-Viewer.git\",\n" +
            "    \"clone_url\": \"https://github.com/SerhiyPetrosyuk/Github-Repository-Viewer.git\",\n" +
            "    \"svn_url\": \"https://github.com/SerhiyPetrosyuk/Github-Repository-Viewer\",\n" +
            "    \"homepage\": null,\n" +
            "    \"size\": 0,\n" +
            "    \"stargazers_count\": 0,\n" +
            "    \"watchers_count\": 0,\n" +
            "    \"language\": \"Java\",\n" +
            "    \"has_issues\": true,\n" +
            "    \"has_downloads\": true,\n" +
            "    \"has_wiki\": true,\n" +
            "    \"has_pages\": false,\n" +
            "    \"forks_count\": 0,\n" +
            "    \"mirror_url\": null,\n" +
            "    \"open_issues_count\": 0,\n" +
            "    \"forks\": 0,\n" +
            "    \"open_issues\": 0,\n" +
            "    \"watchers\": 0,\n" +
            "    \"default_branch\": \"master\"\n" +
            "  }" +
            "]";

    // endregion

    private EntityJsonMapper jsonMapper;

    @Before
    public void setUp(){
        jsonMapper = new EntityJsonMapper();
        assertNotNull(jsonMapper);
    }

    @Test
    public void testTransformUser(){
        Item userEntity = jsonMapper.transformUser(userJson);
        assertNotNull(userEntity);
    }

    @Test
    public void testTransformRepository(){
        Collection<RepositoryEntity> repositoryEntities;
        repositoryEntities = jsonMapper.transformRepositoryCollection(repositoryJson);

        assertTrue(repositoryEntities.size() > 0);
    }
}
