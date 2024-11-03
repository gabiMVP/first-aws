import axios from 'axios';


export const getCusomers = async() =>{
    try{
        return await axios.get(`${import.meta.env.VITE_API_BASE_URL}/api/v1/customers`)
    }catch(e){
        throw e
    }

}