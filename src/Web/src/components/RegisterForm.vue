<template>
  <form @submit.prevent="register">
    <input type="text" v-model="username" placeholder="Username" required />
    <input type="password" v-model="password" placeholder="Password" required />
    <button type="submit">Register</button>
    <p v-if="errorMessage" style="color: red">{{ errorMessage }}</p>
    <p v-if="successMessage" style="color: green">{{ successMessage }}</p>
  </form>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: '',
      errorMessage: '',
      successMessage: ''
    }
  },
  methods: {
    async register() {
      this.errorMessage = ''
      this.successMessage = ''
      try {
        const res = await fetch('http://localhost:8080/api/s3/v1/user/register', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username: this.username, password: this.password })
        })
        const result = await res.json()
        if (res.ok && result === true) {
          this.successMessage = 'Registered successfully!'
          this.$emit('registered')
        } else if (res.status === 409) { // conflict (duplicate user)
          this.errorMessage = 'Username already exists'
        } else {
          this.errorMessage = 'Registration failed'
        }
      } catch (err) {
        console.error(err)
        this.errorMessage = 'Registration failed: server error'
      }
    }
  }
}
</script>

<style>
form { display: flex; flex-direction: column; width: 250px; }
input, button { margin: 5px 0; }
</style>
