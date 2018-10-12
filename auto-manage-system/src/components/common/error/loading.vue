<template>
        <div class="error-page">
            <div class="error-code">正<span>在</span>加<span>载</span>中</div>
            <hr/>
        </div>
</template>

<script>
    export default {
        name: "loading"
    }
</script>

<style scoped>
   h1 {
       font-size: xx-small;
       text-align: center;
   }
   .error-page {
       display: flex;
       justify-content: center;
       align-items: center;
       flex-direction: column;
       width: 100%;
       height: 100%;
       background: #f3f3f3;
       box-sizing: border-box;
   }
   .error-code {
       line-height: 1;
       font-size: 250px;
       font-weight: bolder;
       color: #f02d2d;
   }

   .error-code span {
       color: #00a854;
   }
</style>
