<template>
    <div style="...">
        <span class="time" id="time">
            今日は：<span class="date">{{ nowTime }}</span>
            <span class="hour" style="...">{{ time.hour }}</span>
            <a class="split">:</a>
            <span class="minutes">{{ time.minutes }}</span>
            <a class="split">:</a>
            <span class="seconds">{{ time.seconds }}</span>
        </span>
    </div>
</template>

<script>
    export default {
        props: ["s"],
        data() {
            return {
                time: {
                    hour: "",
                    minitus: "",
                    seconds: ""
                },
                nowTime: "",
                week: [
                    "月曜日",
                    "火曜日",
                    "水曜日",
                    "木曜日",
                    "金曜日",
                    "土曜日",
                    "日曜日",

                ]
            };
        },
        mounted() {
            this.dateTime();
        },
        methods: {
            dataTime() {
                this.timeFormate();
                setTimeout(() => {
                    this.dateTime();

                }, 1000);
            },
            timeFormate() {
                const newtime = new Date();
                this.time.hour = this.getIncrease(newtime.getHours(),2);
                this.time.minutes = this.getIncrease(newtime.getMinutes(),2);
                this.time.minutes = this.getIncrease(newtime.getMinutes(),2);
                this.nowTime = 
                    this.getIncrease(newtime.getFullYear(),4) + "年" + 
                    this.getIncrease(newtime.getMonth() + 1, 2) + "月" + 
                    this.getIncrease(newtime.getDate(), 2) + "日" + 
                    this.week[newtime.getDate()];
            },
            getIncrease(num,digit) {
                var increase = "";
                for (var i = 0; i < digit; i++) {
                    increase += "0";
                }
                return (increase + num).slice(-digit);
            }
        }
    }
</script>

<style scoped>
    .text-data .time {
        font-size: 1rem;
        margin-right: 0.5rem;
    }
    .split {
        animation: shark 1s step-end infinite;
        vertical-align: center;
        margin-left: 2px;
        margin-right: 2px;
    }
    @keyframes shark {
        0%,
        100% {
            opacity: 1;
        }
        50% {
            opacity: 0;
        }
    }
</style>