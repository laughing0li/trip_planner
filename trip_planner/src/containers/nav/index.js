import React, { Component } from 'react'
import { Menu, Container } from 'semantic-ui-react'
import { Link, Route, Switch, Redirect } from 'react-router-dom'
import Weather from '../weather'
import Trip from '../trip'
import './index.css'

export default class Nav extends Component {
    state = { activeItem: 'weather' }

    handleItemClick = (e, { name }) => {
        this.setState({ activeItem: name })
    }

    render() {
        const { activeItem } = this.state
        return (
            <div>
                <Menu style={{ background: '#31364A' }} inverted secondary stackable>
                    <Container>
                        <Link to='/weather'>
                            <Menu.Item style={{ height: 65 }}
                                name='weather'
                                active={activeItem === 'weather'}
                                onClick={this.handleItemClick}
                                as='div'
                            >
                                Weather
                            </Menu.Item>
                        </Link>
                        <Link to='/trip' >
                            <Menu.Item style={{ height: 65 }}
                                name='trip'
                                active={activeItem === 'trip'}
                                onClick={this.handleItemClick}
                                as='div'
                            >
                                Trip
                            </Menu.Item>
                        </Link>
                        
                    </Container>
                </Menu>
                <div>
                    <Switch>
                        <Route path='/weather' component={Weather} />
                        <Route path='/trip' component={Trip} />
                        <Redirect to='/weather' />
                    </Switch>
                </div>
            </div>

        )
    }
}
