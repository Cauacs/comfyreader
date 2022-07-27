
const FeedItem = (props) =>  {
    return(
        <div key={props.item.title}>
            <p>title: {props.item.title}</p>
            <p>description : {props.item.description}</p>
        </div>
    )
}

export default FeedItem