import { Box, Chip, Container, Fab, List, ListItem, Paper, Typography } from "@mui/material"
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { useEffect, useState } from "react";
import api from "../service/api";


const Urls = () => {
      const [open, setOpen] = useState(false)
      const [urls, setUrls] = useState();

      useEffect(() => {
        const fetchData = async () => {
            await api.get("/users/urls")
            .then(res => setUrls(res.data))
            .catch(err => {console.log(err); alert(err)})
        };
        fetchData()
      }, [])

      const handleAddUrl = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        await api.post("/users/urls", {
            url : data.get('url')
        })
        .then(res => {
            console.log(res)
            window.location.reload()
        })
        .catch(err => console.log(err))

      }
      
      const handleDeleteUrl = async(event, url) => {
        event.preventDefault()
        console.log(url)
        await api.delete("/users/urls", {
            "url" : url
        })
        .then(res => {
            console.log(res)
            window.location.reload()
        })
        .catch(err => console.log(err))
      }

      const handleClickOpen = () => {
        setOpen(true);
      };
    
      const handleClose = () => {
        setOpen(false);
      };

    return(
        <>  
            <Container maxWidth="md" sx={{margin : "auto", width : "50%", padding : "30px"}}>
                <Paper sx={{width: '100%', margin : "auto"}}>
                    <Container maxWidth="xl">
                        <List sx={{width : "50%", margin : "auto", textAlign : "center"}}>
                            <Typography variant="h4" align="center" sx={{marginBottom : "20px"}}>
                                List of urls
                            </Typography>
                            {urls && urls.map((url) => (
                                <ListItem sx={{display : "flex", justifyContent: "center"}}
                                key={url}>
                                    <Chip
                                        id="123"
                                        label={url}
                                        onDelete={(event) => handleDeleteUrl(event, url)}
                                        deleteIcon={<DeleteIcon />}
                                    />
                                </ListItem>
                            ))}
                        </List>
                    </Container>
                    <Fab color="white" aria-label="add" size="small" sx={{margin : "20px"}} onClick={handleClickOpen}>
                        <AddIcon />
                    </Fab>
                </Paper>
            </Container>


            <Dialog open={open} onClose={handleClose} onSubmit={handleAddUrl} component="form">
                <DialogTitle>Add Url</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="url"
                        name="url"
                        label="RSS Feed URL"
                        fullWidth
                        variant="standard"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button type="submit">Add</Button>
                </DialogActions>
            </Dialog>
            <pre>{JSON.stringify(urls, null, 2)}</pre>
        </>
    )
}

export default Urls