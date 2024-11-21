import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { Button, ButtonGroup ,Spinner , Text,  Wrap, WrapItem  } from '@chakra-ui/react'
import {useEffect } from 'react'
import {getCusomers} from './services/client.js'
import SidebarWithHeader from './components/shared/Sidebar.jsx'
import Card from './components/Card.jsx'
import DrawerForm from './components/DrawerForm.jsx'
function App() {

   const [customers, setCustomers] = useState([]);
   const[loading,setLoading] = useState(false);

    const fetchCustomers = () =>{
               setLoading(true)
            getCusomers().then( res => {
                 setCustomers(res.data)
                }).catch(err => {
                    console.log(err)
                    }).finally( ()=>{
                        setLoading(false)
                        }
                    )


        }

   useEffect( ()=>{
        fetchCustomers();
       },[] )

   if(loading){
       return(

       <Spinner
         thickness='4px'
         peed='0.65s'
           emptyColor='gray.200'
           color='blue.500'
           size='xl'
           />

        )
    }
    if(customers.length <=0 ){
        return(
            <div>
                <Text>No customers</Text>
            <SidebarWithHeader>
                <DrawerForm fetchCustomers = {fetchCustomers}/>
            </SidebarWithHeader>
            </div>
            )

        }

  return (

        <div>
          <SidebarWithHeader>
              <Wrap>
                  <DrawerForm fetchCustomers = {fetchCustomers} />
                                  {
                                      customers.map( (customer,index)=>(
                                           <WrapItem key = {index}>
                                          <Card
                                           {...customer}
                                            fetchCustomers = {fetchCustomers}
                                          />
                                           </WrapItem>
                                          )
                                      )
                                  }

              </Wrap>
          </SidebarWithHeader>

          </div>
  )
}

export default App
