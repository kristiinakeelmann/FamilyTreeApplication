import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Container, Form, FormGroup, Input, Label, Alert, Spinner, Card, CardText, CardLink, CardTitle, CardGroup} from 'reactstrap';
import AppNavbar from './AppNavbar';
import FemaleAvatar from './illustrations/undraw_female_avatar_w3jk.svg';
import MaleAvatar from './illustrations/undraw_male_avatar_323b.svg';
import AncestorAvatar from './illustrations/undraw_gradma_wanr.svg';

class StatisticsView extends Component {

    constructor(props) {
        super(props);
        this.state = {
            persons: [],
            youngestAunt: [],
            youngestUncle: [],
            mostAncestors: [],
            birthOrder: [],
            ancestorsNames: [],
        };

        this.handlePersonChange = this.handlePersonChange.bind(this);

    }

    async componentDidMount() {
        this.setState({isLoading: true});
        this.setState({selectedPersonId: 1});
        this.getYoungestAunt();
        this.getYoungestUncle();
        this.loadMostAncestors();

        const allPersons = await fetch('/persons')
            .then(response => response.json());
        this.setState({persons: allPersons, isLoading: false});

    }

    getPerson(id) {
        return this.state.persons.filter(person => person.id == id)[0];
    }

    handlePersonChange(event) {
        event.preventDefault();
        const selectedPersonId = event.target.value;
        this.setState({selectedPersonId: selectedPersonId});
        this.getBirthOrder(selectedPersonId);

    }

    getYoungestAunt() {

        let method = 'GET';
        let path = '/youngestaunt';

        fetch(path, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
            .then(response => response.json())
            .then(data => this.setState({youngestAunt: data, isLoading: false}));
    }

    getYoungestUncle() {

        let method = 'GET';
        let path = '/youngestuncle';

        fetch(path, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
            .then(response => response.json())
            .then(data => this.setState({youngestUncle: data, isLoading: false}));
    }

    getBirthOrder(selectedPersonId) {

        let method = 'GET';
        let path = '/birthorder/' + selectedPersonId;

        fetch(path, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
            .then(response => response.json())
            .then(data => this.setState({birthOrder: data, isLoading: false}));
    }

    async loadMostAncestors() {

        const mostAncestors = await fetch('/mostancestors')
            .then(response => response.json());
        this.setState({mostAncestors: mostAncestors, isLoading: false});

        const ancestorsNames = await fetch('/ancestornames/' + mostAncestors.id)
            .then(response => response.json());
        this.setState({ancestorsNames: ancestorsNames, isLoading: false});


    }

    getErrors() {

        const selectedPerson = this.getPerson(this.state.selectedPersonId);
        if (selectedPerson == null) {
            return "Please select person";
        }

    }

    render() {
        const persons = this.state.persons;
        const isLoading = this.state.isLoading;
        const youngestAunt = this.state.youngestAunt.firstName;
        const youngestAuntId = this.state.youngestAunt.id;
        const youngestUncle = this.state.youngestUncle.firstName;
        const youngestUncleId = this.state.youngestUncle.id;
        const birthOrder = this.state.birthOrder.toString();
        const mostAncestors = this.state.mostAncestors.firstName;
        const mostAncestorsId = this.state.mostAncestors.id;
        const ancestorsNames = this.state.ancestorsNames;

        if (isLoading) {
            return <Spinner style={{width: '3rem', height: '3rem'}} type="grow"/>;
        }

        let optionItems = persons
            .map(person => <option key={person.id}
                                   value={person.id}>{person.firstName + ' ' + person.lastName}</option>);

        const title = <h2>{'Answers to the most burning questions'}</h2>;
        const errorMessage = this.getErrors();

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                {errorMessage ? <Alert color="warning"> {errorMessage} </Alert> : null}
                <Form>
                    <FormGroup>
                        <Label for="exampleSelect">Choose person to check the birth order</Label>
                        <Input type="select" name="selectPerson" id="exampleSelect"
                               value={this.state.selectedPersonId ? this.state.selectedPersonId : null}
                               onChange={this.handlePersonChange}>
                            <option value=""></option>
                            {optionItems}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        {birthOrder}
                    </FormGroup>
                </Form>
                <CardGroup>
                    <Card body className="text-center">
                        <CardTitle>Youngest Aunt</CardTitle>
                        <img class="center" src={FemaleAvatar} alt="FemaleAvatar"/>
                        <CardText>{youngestAunt}</CardText>
                        <CardLink href="#" tag={Link} to={"/person/" + youngestAuntId}>Check her details</CardLink>
                    </Card>
                    <Card body className="text-center">
                        <CardTitle>Youngest Uncle</CardTitle>
                        <img class="center" src={MaleAvatar} alt="MaleAvatar"/>
                        <CardText>{youngestUncle}</CardText>
                        <CardLink href="#" tag={Link} to={"/person/" + youngestUncleId}>Check his details</CardLink>
                    </Card>
                </CardGroup>
                <CardGroup>
                    <Card body className="text-center">
                        <CardTitle>Most ancestors</CardTitle>
                        <img class="center" src={AncestorAvatar} alt="AncestorAvatar"/>
                        <CardText>{mostAncestors}</CardText>
                        <CardText>{ancestorsNames}</CardText>
                        <CardLink href="#" tag={Link} to={"/person/" + mostAncestorsId}>Check details</CardLink>
                    </Card>
                </CardGroup>
            </Container>
        </div>
    }
}

export default withRouter(StatisticsView);