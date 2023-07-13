<template>
  <div>
    <board-list-table></board-list-table>
  </div>
</template>

<script>
import BoardListTable from "@/components/BoardList/BoardListTable.vue";

export default {
  name: "BoardListView",
  components: {
    BoardListTable
  }
}
</script>

<style lang="scss" scoped>
</style>