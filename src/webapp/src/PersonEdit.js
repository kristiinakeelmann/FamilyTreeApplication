import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './AppNavbar';

class PersonEdit extends Component {

    emptyItem = {
        firstName: '',
        lastName: '',
        sex: '',
        dateOfBirth: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const person = await (await fetch(`/person/${this.props.match.params.id}`)).json();
            this.setState({item: person});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }


    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        let method;
        let path;

        if (item.id != null) {
            method = 'PUT';
            path = '/person/' + item.id;
        } else {
            method = 'POST';
            path = '/person/';
        }

        await fetch(path, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),

        }).then(function (response) {
            if (response.status == 400) {
                return response.json();
            }
        }).then(function (object) {
            if (object != null) {
                alert(object.errors[0].defaultMessage)
            }
        })

        this.props.history.push('/persons');
    }


    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Person' : 'Add Person'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="firstName">First name</Label>
                        <Input type="text" name="firstName" id="firstName" value={item.firstName || ''}
                               onChange={this.handleChange} autoComplete="firstName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="lastName">Last name</Label>
                        <Input type="text" name="lastName" id="lastName" value={item.lastName || ''}
                               onChange={this.handleChange} autoComplete="lastName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="sex">Sex</Label>
                        <div>
                            <Input type="radio" name="sex" id="sexMale" value={item.sex || 'male'}
                                   onChange={this.handleChange} autoComplete="sex"/> Male
                            <Input type="radio" name="sex" id="sexFemale" value={item.sex || 'female'}
                                   onChange={this.handleChange} autoComplete="sex"/> Female
                        </div>
                    </FormGroup>
                    <FormGroup>
                        <Label for="dateOfBirth">Birthday</Label>
                        <Input type="text" name="dateOfBirth" id="dateOfBirth" value={item.dateOfBirth || ''}
                               onChange={this.handleChange} autoComplete="dateOfBirth"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/persons">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(PersonEdit);