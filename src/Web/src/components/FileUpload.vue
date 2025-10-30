<template>
  <form @submit.prevent="uploadFile">
    <input type="file" @change="handleFileChange" required />
    <input type="text" v-model="fileName" placeholder="File name" required />
    <button type="submit">Upload</button>
  </form>
</template>

<script>
export default {
  data() {
    return {
      selectedFile: null,
      fileName: ''
    }
  },
  methods: {
    handleFileChange(event) {
      this.selectedFile = event.target.files[0]
    },
    async uploadFile() {
      if (!this.selectedFile || !this.fileName) return

      const formData = new FormData()
      formData.append('file', this.selectedFile)
      formData.append('name', this.fileName)
      formData.append('description', 'Uploaded via dashboard')

      try {
        const res = await fetch('http://localhost:8080/api/s3/v1/upload/file', {
          method: 'POST',
          body: formData
        })
        if (res.ok) {
          this.$emit('uploaded', `File "${this.fileName}" uploaded successfully!`)
          this.fileName = ''
          this.selectedFile = null
        } else {
          this.$emit('uploaded', `Upload failed`)
        }
      } catch (err) {
        console.error(err)
        this.$emit('uploaded', 'Server error during upload')
      }
    }
  }
}
</script>

<style>
form { display: flex; flex-direction: column; width: 300px; margin-top: 20px; }
input, button { margin: 5px 0; }
</style>
