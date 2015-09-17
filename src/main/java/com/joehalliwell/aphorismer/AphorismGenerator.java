package com.joehalliwell.aphorismer;

import java.util.Random;

public class AphorismGenerator {

    static String[][] conceptGroups = new String[][]{
            {"science", "art", "history", "politics", "mathematics", "philosophy"},
            {"joy", "despair"},
            {"truth", "beauty", "honour", "life", "death"},
            {"love", "hatred"},
            {"war", "peace"},
            {"creativity", "plagiarism"},
            {"theft", "charity"},
            {"birth", "death"},
            {"murder", "art"},
            {"thinking", "dreaming", "believing", "doubting", "hoping"},
            {"cowardice", "valour", "discretion"},
            {"charity", "courage", "prudence", "honesty", "justice", "kindness"},
            {"remembering", "forgetting"},
            {"youth", "age"},
            {"comedy", "religion", "reality"},
            {"God", "the Devil"},
            {"heaven", "hell"},
            {"Africa", "America", "Europe", "India"},
            {"modernity", "antiquity"},
            {"belief", "thought", "hope", "sensation", "imagination", "knowledge", "justification", "fancy", "conviction"},
            {"justice", "law"},
            {"academia", "industry"},
            {"form", "content"},
            {"loving", "leaving"},
            {"music", "mathematics"},
            {"literature", "fact"},
            {"fact", "fiction"},
            {"prose", "poetry"},
            {"engineering", "craft"},
            {"leaving", "arriving"},
            {"chaos", "order"},
            {"society", "family"},
            {"work", "leisure"},
            {"wealth", "poverty"},
            {"success", "failure"},
            {"language", "silence"},
            {"truth", "gossip"},
            {"self-reference", "aphorism"},
            {"autonomy", "slavery"},
            {"humanity", "religion"},
            {"property", "theft"},
            {"slavery", "grace"},
            {"ambition", "success"},
            {"unity", "multiplicity"},
            {"nothingness", "being"},
            {"disease", "health"},
            {"candour", "intrigue"},
            {"necessity", "contingency"},
            {"debate", "monologue"},
            {"obedience", "will"},
            {"chance", "fate"},
            {"safety", "security"},
            {"life", "living"}
    };

    static String[] frames = new String[]{
            "$a is a malady for which $b is the cure.",
            "$a wouldn't be $a if it weren't for $b.",
            "$a delivers us from $b, but who will deliver us from $a?",
            "$a adds to our pains, $b to our pleasures.",
            "Happiness is in $a, not in $b.",
            "$a unites men, $b divides them.",
            "$a deceives us more often than $b.",
            "A little $a is a dangerous thing, a great deal of it is absolutely fatal.",
            "$a is the $b of the common man.",
            "$a is the shadow of $b.",
            "Where $a rejoices, $b despairs.",
            "$a is the mistress of $b.",
            "Behind $a there is always $b.",
            "When $a sleeps, it dreams of $b.",
            "Where can we find $a? There we find $b also.",
            "$a and truth are continents divided by an ocean of $b.",
            "When $a was created, $b was the mould.",
            "$a is the meat, $b the mustard.",
            "$a is the enemy of $b.",
            "When $a dines out, $b foots the bill.",
            "$a is the chalk; $b the blackboard.",
    };

    final long seed;

    public AphorismGenerator(long seed) {
        this.seed = seed;
    }

    public String getAphorism(long index) {
        Random rng = new Random(seed + index);
        String frame = frames[rng.nextInt(frames.length)];
        String[] conceptGroup = conceptGroups[rng.nextInt(conceptGroups.length)];
        String a = conceptGroup[rng.nextInt(conceptGroup.length)];
        String b = a;
        while (a.equals(b)) {
            b = conceptGroup[rng.nextInt(conceptGroup.length)];
        }
        String result = frame.replace("$a", a).replace("$b", b);
        result = result.substring(0,1).toUpperCase() + result.substring(1);
        return result;
    }

    public long getSeed() {
        return seed;
    }

    public static void main(String[] args) {
        long conceptCount = 1;
        for (String[] conceptGroup : conceptGroups) {
            conceptCount += conceptGroup.length * (conceptGroup.length - 1);
        }
        long frameCount = 0;
        for (String frame: frames) {
            frameCount += frame.contains("$b") ? 1 : 0.5;
        }
        long total = frameCount * conceptCount;
        System.out.println("Possibilities: " + total);
    }
}
