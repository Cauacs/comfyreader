import { Box, Grid } from "@mui/material"
import Post from "../Post/Post"

const Posts = () => {
    return (
        <Box sx={{ width: '80%', margin: "auto", marginTop: "20px" }}>
            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                <Grid item xs={12} sm={4} >
                    <Post />
                </Grid>
                <Grid item xs={12} sm={4} >
                    <Post />
                </Grid>
                <Grid item xs={12} sm={4} >
                    <Post />
                </Grid>
                <Grid item xs={12} sm={4} >
                    <Post />
                </Grid>
            </Grid>
        </Box>
    )
}

export default Posts