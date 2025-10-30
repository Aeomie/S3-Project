<template>
  <form @submit.prevent="login">
    <input type="text" v-model="username" placeholder="Username" required />
    <input type="password" v-model="password" placeholder="Password" required />
    <button type="submit">Login</button>
    <p v-if="errorMessage" style="color: red">{{ errorMessage }}</p>
  </form>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: '',
      errorMessage: ''
    }
  },
  methods: {
    async login() {
      this.errorMessage = ''
      try {
        const res = await fetch('http://localhost:8080/api/s3/v1/user/login', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username: this.username, password: this.password })
        })
        const result = await res.json()
        if (res.ok && result === true) {
          // Navigate to upload page
          this.$router.push('/upload')
        } else {
          this.errorMessage = 'Login failed: incorrect username or password'
        }
      } catch (err) {
        console.error(err)
        this.errorMessage = 'Login failed: server error'
      }
    }
  }
}
</script>

<style>
form { display: flex; flex-direction: column; width: 250px; }
input, button { margin: 5px 0; }
</style>
