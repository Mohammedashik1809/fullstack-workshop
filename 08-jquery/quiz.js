$(document).ready(function () {

    let quizData = [];
    let currentQuestion = 0;
    let score = 0;
    let userAnswers = [];
    let timer;
    let timeLeft = 30;

    // Simulated AJAX call
    $.getJSON("questions.json")
        .done(function (data) {
            quizData = data;
            initQuiz();
        })
        .fail(function () {
            alert("Failed to load quiz data");
        });

    function initQuiz() {
        createIndicators();
        loadQuestion();
        startTimer();
    }

    function createIndicators() {
        $(".question-indicators").empty();
        quizData.forEach((_, index) => {
            $(".question-indicators").append(
                `<span data-index="${index}">${index + 1}</span>`
            );
        });
    }

    function loadQuestion() {
        resetTimer();

        const q = quizData[currentQuestion];
        $("#question-text").text(q.question);
        $(".options").empty();

        q.options.forEach((opt, index) => {
            $(".options").append(
                `<li data-index="${index}">${opt}</li>`
            );
        });

        updateProgress();
        updateIndicators();

        if (currentQuestion === quizData.length - 1) {
            $("#next").hide();
            $("#submit").show();
        } else {
            $("#next").show();
            $("#submit").hide();
        }
    }

    function updateProgress() {
        let percent = ((currentQuestion + 1) / quizData.length) * 100;
        $(".progress-bar").animate({ width: percent + "%" }, 300);
    }

    function updateIndicators() {
        $(".question-indicators span")
            .removeClass("active")
            .eq(currentQuestion)
            .addClass("active");
    }

    function startTimer() {
        timer = setInterval(() => {
            timeLeft--;
            $("#time").text(timeLeft);

            if (timeLeft <= 10) {
                $(".timer").addClass("warning");
            }

            if (timeLeft === 0) {
                clearInterval(timer);
                nextQuestion();
            }
        }, 1000);
    }

    function resetTimer() {
        clearInterval(timer);
        timeLeft = 30;
        $("#time").text(timeLeft);
        $(".timer").removeClass("warning");
        startTimer();
    }

    // Event Delegation for options
    $(".options").on("click", "li", function () {
        $(".options li").removeClass("selected");
        $(this).addClass("selected");
        userAnswers[currentQuestion] = $(this).data("index");
    });

    $("#next").click(nextQuestion);

    $("#prev").click(function () {
        if (currentQuestion > 0) {
            currentQuestion--;
            loadQuestion();
        }
    });

    function nextQuestion() {
        if (currentQuestion < quizData.length - 1) {
            currentQuestion++;
            loadQuestion();
        }
    }

    $(".question-indicators").on("click", "span", function () {
        currentQuestion = $(this).data("index");
        loadQuestion();
    });

    $("#submit").click(showResults);

    function showResults() {
        clearInterval(timer);
        $("#question-text, .options, .navigation, .timer, .progress, .question-indicators").fadeOut();

        score = 0;
        quizData.forEach((q, i) => {
            if (userAnswers[i] === q.correctAnswer) {
                score++;
            }
        });

        let percentage = Math.round((score / quizData.length) * 100);

        let resultHTML = `
            <h2>Your Score: ${score}/${quizData.length}</h2>
            <p>Percentage: ${percentage}%</p>
            <button id="restart">Restart Quiz</button>
        `;

        $("#result").html(resultHTML).fadeIn();
    }

    $("#result").on("click", "#restart", function () {
        location.reload();
    });

});




