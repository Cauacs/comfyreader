import Post from '../Post/post'
import SinglePost from '../SinglePost/singlePost'
import './posts.css'

export default function Posts() {
    return (
        <div className='posts'>
            {/* <Post/>
            <Post/>
            <Post/>
            <Post/>
            <Post/>
            <Post/> */}
            <SinglePost/>
        </div>
    )
}