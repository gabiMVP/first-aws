import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import {
 FormLabel,Input,Alert,Box,Button
} from '@chakra-ui/react';

import {updateCustomer} from '../services/client.js'

const MyTextInput = ({ label, ...props }) => {
  // useField() returns [formik.getFieldProps(), formik.getFieldMeta()]
  // which we can spread on <input>. We can use field meta to show an error
  // message if the field is invalid and it has been touched (i.e. visited)
  const [field, meta] = useField(props);
  return (
    <Box>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <Input className="text-input" {...field} {...props} />
      {meta.touched && meta.error ? (
        <Alert className="error">{meta.error}</Alert>
      ) : null}
    </Box>
  );
};


const MySelect = ({ label, ...props }) => {
  const [field, meta] = useField(props);
  return (
    <Box>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <select {...field} {...props} />
      {meta.touched && meta.error ? (
        <Alert className="error">{meta.error}</Alert>
      ) : null}
    </Box>
  );
};

// And now we can use these
const UpdateCustomerForm = (  {customer,fetchCustomers}  ) => {
    const originalCustomer = JSON.parse(JSON.stringify(customer));
    console.log("PLOP!!!" +originalCustomer.id );

  return (
    <>
      <h1>Subscribe!</h1>
      <Formik
        initialValues={{
          name: customer.name,
          email: customer.email,
          age : parseInt(customer.age),
          gender: customer.gender,
        }}
        validationSchema={Yup.object({
          name: Yup.string()
            .max(15, 'Must be 15 characters or less')
            .required('Required'),
          email: Yup.string()
            .email('Invalid email address')
            .required('Required'),
          age: Yup.number()
          .min(16, 'must be at least 16'),
          gender: Yup.string()
            .oneOf(
              ['MALE', 'FEMALE'],
              'Invalid gender'
            )
            .required('Required'),
        })}
        onSubmit={(customer, { setSubmitting }) => {
            customer.id = originalCustomer.id;
            setSubmitting(true)
          setTimeout(() => {
            alert(JSON.stringify(customer, null, 2));
            updateCustomer(customer).then(res =>{
                 alert("customer Updated")
                 console.log(res)
                 fetchCustomers();
                }).catch(err => {
                    alert(err)
                    }).finally( () => {
                        setSubmitting(false)

                        }  )
            setSubmitting(false);
          }, 400);
        }}
      >
        {({isValid,isSubmitting}) => (
            <Form>
                      <MyTextInput
                        label="name"
                        name="name"
                        type="text"
//                         placeholder="Jane"
                      />
                      <MyTextInput
                        label="Email Address"
                        name="email"
                        type="email"
//                         placeholder="jane@formik.com"
                      />
                      <MyTextInput
                         label="age"
                        name="age"
                        type="number"
//                         placeholder="0"
                      />
                      <MySelect label="gender" name="gender">
                        <option value="">Select a gender </option>
                        <option value="MALE">MALE</option>
                        <option value="FEMALE">FEMALE</option>
                      </MySelect>


                      <Button disabled = {!isValid || isSubmitting }type="submit">Submit</Button>
                    </Form>

            )}
      </Formik>
    </>
  );
};

export default UpdateCustomerForm;