import './post.css'

//talvez adicionar categorias para cada post.

export default function Post() {
    return (
        <div className='post'>
            <div className="postInfo">
                <span className="postTitle">
                    Lorem ipsum dolor, sit amet consectetur adipisic
                </span>
                <hr/>
                <span className="postDate">1 hour ago</span>
            </div>
            <div className="postDesc">
            "Frenzied raves. Crowded bars (with free therapy). And of course, cuddle parties. Nightlife is returning to Ukraine’s capital. But revelers still have to reckon with guilty feelings. Plus curfew."
            </div>
        </div>
    )
}