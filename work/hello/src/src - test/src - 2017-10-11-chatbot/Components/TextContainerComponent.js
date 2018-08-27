import React from 'react'
import { Container } from 'semantic-ui-react'

const ContainerExampleContainer = React.createClass({
  // This is React lifecyle callback called to render any component.
  render () {
    return (
      <Container text style={{textAlign: 'center'}}>
        <p>{this.props.message}</p>
      </Container>
    )
  }
})

export default ContainerExampleContainer
