package xyz.comfyreader.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.comfyreader.models.Feed;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.rometools.rome.feed.synd.SyndEntry;
import xyz.comfyreader.models.FeedItem;
import xyz.comfyreader.models.User;
import xyz.comfyreader.repositories.UserRepository;

@Service
public class FeedService {

    private final UserRepository userRepository;
    List<Feed> feeds = new ArrayList<>();

    public FeedService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    //add feed to feed list
    public void addFeed(String urlString) {
        try{
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(new URL(urlString)));
            List<FeedItem> items = normalizeItems(feed);
            Feed newFeed = new Feed(
                    feed.getTitle(),
                    feed.getLink(),
                    feed.getDescription(),
                    feed.getCopyright(),
                    feed.getPublishedDate(),
                    items
            );
            if(!feeds.contains(newFeed))
                feeds.add(newFeed);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private List<FeedItem> normalizeItems(SyndFeed feed){
        List<FeedItem> items = new ArrayList<>();
        for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
            FeedItem item = new FeedItem(
                    entry.getTitle(),
                    entry.getDescription().getValue(),
                    entry.getLink()
            );
            items.add(item);
        }
        return items;
    }

    private void formFeed(List<String> urls){
        urls.forEach(this::addFeed);
    }

    public List<Feed> fetchFeed(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        optionalUser.ifPresentOrElse(
                user -> {formFeed(user.urls());},
                () -> {throw new IllegalStateException("User not found");}
        );
        return feeds;
    }
}
