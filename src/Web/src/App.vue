<template>
  <div>
    <h1>Welcome to My App</h1>
    <router-view /> <!-- This renders the component based on the URL -->
    <p>{{ message }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return { message: '' }
  },
  methods: {
    showMessage(msg) { this.message = msg }
  }
}
</script>
