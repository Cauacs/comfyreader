import api from '../service/api'
import { useEffect, useState } from 'react';
import Feed from '../Feed/Feed';

function Feeds() {
  const [feeds, setFeeds] = useState(null)
  useEffect(() => {
    const fetchData = async () => {
      await api.get(`feeds/`,{
        auth : {
          username : "corno@mst.com",
          password : "123"
        }
      })
      .then((res) => {setFeeds(res.data); console.log(res.data)})
      .catch(e => console.log(e))
    }
    fetchData();
  }, [])

  return (
      <div>
        {feeds && feeds.map(feed => (<Feed feed={feed}/>))}
      </div>
  );
}

export default Feeds;