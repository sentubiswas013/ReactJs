import React from 'react'
import { Loader } from 'semantic-ui-react'

class CustomLoader extends React.Component {
  // This is React lifecyle callback called to render any component.
  // We display a loader indicating background activity.
  render () {
    return (
      <Loader active inline='centered'
        size='massive'>Loading...</Loader>
    )
  }
}

export default CustomLoader
