import FeedItem from "./FeedItem"

const Feed = (props) => {
    return(
        <div key={props.feed.title}>
            <h2>{props.feed.title}</h2>
            {props.feed.items.map(item => (<FeedItem item={item} />))}
        </div>
    )
}

export default Feed