import React, {Component} from 'react';
import { Nav, Navbar, NavbarBrand, NavItem, NavLink, Container} from 'reactstrap';
import {Link} from 'react-router-dom';

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return <Navbar color="dark" dark expand="md">
            <Container>
            <NavbarBrand tag={Link} to="/persons">Home</NavbarBrand>
            <NavbarBrand tag={Link} to="/person/new">Add Person</NavbarBrand>
            <NavbarBrand tag={Link} to="/relation/new">Add Relation</NavbarBrand>
            <NavbarBrand tag={Link} to="/familytree/new">Generate Family Tree</NavbarBrand>
            <NavbarBrand tag={Link} to="/statistics">Statistics</NavbarBrand>
                <Nav className="ml-auto" navbar>
                    <NavItem>
                        <NavLink href="https://github.com/kristiinakeelmann/FamilyTreeApplication">GitHub</NavLink>
                    </NavItem>
                </Nav>
            </Container>
        </Navbar>;

    }
}


